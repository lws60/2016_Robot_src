package utility;

import org.usfirst.frc.team4587.robot.commands.AutomaticAim;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ImageType;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.USBCamera;

public class CameraThread extends Thread{
	    private static String ATTR_WB_MODE = "CameraAttributes::WhiteBalance::Mode";
	    private static String ATTR_WB_VALUE = "CameraAttributes::WhiteBalance::Value";
	    private static String ATTR_EX_MODE = "CameraAttributes::Exposure::Mode";
	    private static String ATTR_EX_VALUE = "CameraAttributes::Exposure::Value";
	    private static String ATTR_BR_MODE = "CameraAttributes::Brightness::Mode";
	    private static String ATTR_BR_VALUE = "CameraAttributes::Brightness::Value";
	
        // After running the thread, check this variable to see whether we should shoot.
	private boolean m_foundTarget = false;
	public boolean foundTarget()
	{
		return m_foundTarget;
	}
	private boolean m_weHaveAShot;
	public boolean weHaveAShot()
	{
		return m_weHaveAShot;
	}
	private double m_shotAngleError;
	public double shotAngleError()
	{
		return m_shotAngleError;
	}
	private static double m_desiredLeftGoalAlignment;
	private static double m_leftGoalAlignmentTolerance;

        // After running the thread, if there is no shot, this tells which way to turn.
        // If > 1, image has more space to the right of the goal than to the left, so turn right.
        // If < 1, image has more space to the left, so turn left.
	private double m_sideRatio;
	public double sideRatio()
	{
		return m_sideRatio;
	}

        // Image Processing Objects and Parameters
	private static Image                               m_rawFrame;
	private static Image                               m_binaryFrame;
	private static Image                               m_filtered;
	private static Image                               m_mask;
	
	private static USBCamera targetCam;
	private static Image targetFrame;
	private static int exposure;
	private static int brightness;
	private static int updatedBrightness;
	
	private static int                                 m_cameraSession;
        private static NIVision.ParticleFilterCriteria2    m_particleCriteria[];
        private static NIVision.ParticleFilterOptions2     m_particleOptions;
        private static NIVision.Range                      m_hueRange, m_saturationRange, m_valueRange; // HSV
        private static NIVision.Range m_redRange, m_greenRange, m_blueRange;
	private static double                              m_minWidth;
	private static double                              m_minHeight;
	private static double                              m_minAspect;
	private static double                              m_maxAspect;
	private static double                              m_minFill;
	private static double                              m_maxFill;
	private static double                              m_minSideRatio;
	private static double                              m_maxSideRatio;
	
	private static double m_desiredCenterLine;

        // Call this once from robotInit(), after reading parameters.
        public static void initializeOnce() {
            m_rawFrame    = NIVision.imaqCreateImage(ImageType.IMAGE_RGB, 3/*borderSize*/);
            m_binaryFrame = NIVision.imaqCreateImage(ImageType.IMAGE_U8,  3/*borderSize*/);
            m_filtered = NIVision.imaqCreateImage(ImageType.IMAGE_RGB,  3/*borderSize*/);
            m_mask = NIVision.imaqCreateImage(ImageType.IMAGE_U8,  3/*borderSize*/);
            
            /*USBCamera targetCam = new USBCamera("cam0");
            NIVision.Image targetFrame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB,0);
            
            int exposure = 50;
            int brightness = 50;
            
            if (brightness <= 100 && brightness >=0)
            	   targetCam.setBrightness(brightness);

            	if (exposure <=100 && exposure >= 0)
            	   targetCam.setExposureManual(exposure);

            	targetCam.setWhiteBalanceManual(0);

            	updatedBrightness = targetCam.getBrightness();
            	SmartDashboard.putNumber("Current Brightness", updatedBrightness);
            	targetCam.closeCamera();*/

    	    m_cameraSession = NIVision.IMAQdxOpenCamera (
                                  Parameters.getString("Camera Name","cam0")
                                , NIVision.IMAQdxCameraControlMode.CameraControlModeController
                              );
    	    
    	    NIVision.IMAQdxSetAttributeString(m_cameraSession, ATTR_WB_MODE, "Manual");
    	    long minv = NIVision.IMAQdxGetAttributeMinimumI64(m_cameraSession, ATTR_WB_VALUE);
    	    NIVision.IMAQdxSetAttributeI64(m_cameraSession, ATTR_WB_VALUE, minv);
    	    

    	    double m_exposureValue = 0;
    	    NIVision.IMAQdxSetAttributeString(m_cameraSession, ATTR_EX_MODE, "Manual");
    	    minv = NIVision.IMAQdxGetAttributeMinimumI64(m_cameraSession, ATTR_EX_VALUE);
    	    long maxv = NIVision.IMAQdxGetAttributeMaximumI64(m_cameraSession, ATTR_EX_VALUE);
    	    long val = minv + (long) (((double) (maxv - minv)) * (((double) m_exposureValue) / 100.0));
    	    NIVision.IMAQdxSetAttributeI64(m_cameraSession, ATTR_EX_VALUE, val);
    	    
    	    double m_brightnessValue = 0;
    	    NIVision.IMAQdxSetAttributeString(m_cameraSession, ATTR_BR_MODE, "Manual");
    	    minv = NIVision.IMAQdxGetAttributeMinimumI64(m_cameraSession, ATTR_BR_VALUE);
    	    maxv = NIVision.IMAQdxGetAttributeMaximumI64(m_cameraSession, ATTR_BR_VALUE);
    	    val = minv + (long) (((double) (maxv - minv)) * (((double) m_brightnessValue) / 100.0));
    	    NIVision.IMAQdxSetAttributeI64(m_cameraSession, ATTR_BR_VALUE, val);
    	    
            NIVision.IMAQdxConfigureGrab   (m_cameraSession);
            NIVision.IMAQdxStartAcquisition(m_cameraSession);
        }

        // Call this during each phase initialization, after reading parameters.
        public static void initializeForPhase() {
            m_particleCriteria = new NIVision.ParticleFilterCriteria2[1];
            m_particleCriteria[0] = new NIVision.ParticleFilterCriteria2 (
                                        NIVision.MeasurementType.MT_AREA_BY_IMAGE_AREA
                                      , Parameters.getDouble("Particle Filter Min. Area Percent",0.1)
                                      , Parameters.getDouble("Particle Filter Max. Area Percent",100.0)
                                      , 0 // calibrated
                                      , 0 // exclude
                                    );

            m_particleOptions = new NIVision.ParticleFilterOptions2 (
                                    0 // rejectMatches
                                  , 0 // rejectBorder
                                  , 1 // fillHoles
                                  , 1 // connectivity8
                                );

            m_hueRange        = new NIVision.Range (
                                    Parameters.getInt("HSV Filter Min. Hue",50)
                                  , Parameters.getInt("HSV Filter Max. Hue",130)
                                );
            m_saturationRange = new NIVision.Range (
                                    Parameters.getInt("HSV Filter Min. Saturation",210)
                                  , Parameters.getInt("HSV Filter Max. Saturation",255)
                                );
            m_valueRange      = new NIVision.Range (
                                    Parameters.getInt("HSV Filter Min. Value",10)
                                  , Parameters.getInt("HSV Filter Max. Value",120)
                                );
            
            m_redRange        = new NIVision.Range (
                    Parameters.getInt("RGB Filter Min. RED",225)
                  , Parameters.getInt("RGB Filter Max. RED",255)
                );
            m_greenRange = new NIVision.Range (
                    Parameters.getInt("RGB Filter Min. GREEN",252)
                  , Parameters.getInt("RGB Filter Max. GREEN",255)
                );
            m_blueRange      = new NIVision.Range (
                    Parameters.getInt("RGB Filter Min. BLUE",225)
                  , Parameters.getInt("RGB Filter Max. BLUE",255)
                );

            m_minWidth      = Parameters.getDouble("Goal Filter: Min. Width (pixels)",  15.0);
            m_minHeight     = Parameters.getDouble("Goal Filter: Min. Height (pixels)", 10.0);
            m_minAspect     = Parameters.getDouble("Goal Filter: Min. Aspect Ratio",     1.2);
            m_maxAspect     = Parameters.getDouble("Goal Filter: Max. Aspect Ratio",     2.3);
            m_minFill       = Parameters.getDouble("Goal Filter: Min. Fill Ratio",       0.1);
            m_maxFill       = Parameters.getDouble("Goal Filter: Max. Fill Ratio",       0.4);
            m_minSideRatio  = Parameters.getDouble("Goal Filter: Min. Side Ratio",       0.85);
            m_maxSideRatio  = Parameters.getDouble("Goal Filter: Max. Side Ratio",       0.95);
            
            m_desiredLeftGoalAlignment = Parameters.getInt("Desired Left Edge of Goal", 275);
            m_leftGoalAlignmentTolerance = Parameters.getInt("Tolerance for Left Edge of Goal", 10);
            
            m_desiredCenterLine = 350;
        }

        // Nothing to do in the constructor for a single thread.
	public CameraThread(){
	}

        // Process the current camera image.
	public void run() {
		System.out.println("Camera Thread Run");
		NIVision.IMAQdxGrab(m_cameraSession, m_rawFrame, 1/*waitForNextBuffer*/);
		//CameraServer.getInstance().setImage(m_rawFrame);

		//Threshold the image looking for green bright shiny stuff
		/*NIVision.imaqColorThreshold (
                    m_binaryFrame           // destination frame
                  , m_rawFrame              // source frame
                  , 255                     // replacement value
                  , NIVision.ColorMode.RGB
                  , m_redRange
                  , m_greenRange
                  , m_blueRange
                );*/
		NIVision.imaqColorThreshold (
                m_binaryFrame           // destination frame
              , m_rawFrame              // source frame
              , 255                     // replacement value
              , NIVision.ColorMode.HSV
              , m_hueRange
              , m_saturationRange
              , m_valueRange
            );
		
		//NIVision.imaqLowPass(m_filtered, m_rawFrame, 3, 3, (float) 0.4, m_mask );
		
		//CameraServer.getInstance().setImage(m_rawFrame);
		//Filter out small particles
		CameraServer.getInstance().setImage(m_binaryFrame);
		

		int n_particles = NIVision.imaqCountParticles(m_binaryFrame, 1/*connectivity8*/);
		SmartDashboard.putNumber("UnFiltered Particle Count", n_particles);
		
		NIVision.imaqParticleFilter4 (
                    m_binaryFrame   // destination frame
                  , m_binaryFrame   // source frame
                  , m_particleCriteria
                  , m_particleOptions
                  , null            // NIVision.ROI object
                );

		//Send particle count after filtering to dashboard
		n_particles = NIVision.imaqCountParticles(m_binaryFrame, 1/*connectivity8*/);
		SmartDashboard.putNumber("Filtered Particle Count", n_particles);

                int good_particle = -1;
                double good_left = 0.0;
                double good_right = 0.0;
                double max_area_found = 0;

                for ( int iprt = 0; iprt < n_particles; iprt++) {
                    double top    = NIVision.imaqMeasureParticle(m_binaryFrame, iprt, 0/*calibrated*/,
                                                                 NIVision.MeasurementType.MT_BOUNDING_RECT_TOP);
                    double left   = NIVision.imaqMeasureParticle(m_binaryFrame, iprt, 0/*calibrated*/,
                                                                 NIVision.MeasurementType.MT_BOUNDING_RECT_LEFT);
                    double bottom = NIVision.imaqMeasureParticle(m_binaryFrame, iprt, 0/*calibrated*/,
                                                                 NIVision.MeasurementType.MT_BOUNDING_RECT_BOTTOM);
                    double right  = NIVision.imaqMeasureParticle(m_binaryFrame, iprt, 0/*calibrated*/,
                                                                 NIVision.MeasurementType.MT_BOUNDING_RECT_RIGHT);
                    double area   = NIVision.imaqMeasureParticle(m_binaryFrame, iprt, 0/*calibrated*/,
                                                                 NIVision.MeasurementType.MT_AREA);

		    double width  = right - left;
                    double height = bottom - top;
                    double aspect = width / height;
                    double fill   = area / (width * height);

                   
                    if (area > max_area_found){
                        max_area_found = area;
                        SmartDashboard.putNumber("Biggest Particle Top",    top);
                        SmartDashboard.putNumber("Biggest Particle Left",   left);
                        SmartDashboard.putNumber("Biggest Particle Bottom", bottom);
                        SmartDashboard.putNumber("Biggest Particle Right",  right);
                        SmartDashboard.putNumber("Biggest Particle Area",   area);
                        SmartDashboard.putNumber("Biggest Particle Aspect", aspect);
                        SmartDashboard.putNumber("Biggest Particle Fill",   fill);
                        SmartDashboard.putNumber("Biggest Particle Width",  width);
                        SmartDashboard.putNumber("Biggest Particle Height", height);
                    } 
                    //if (top < 0) continue;
                    //if (bottom > 300) continue;

                    if (width  < m_minWidth ) continue;
                    if (height < m_minHeight) continue;
                    if (aspect < m_minAspect) continue;
                    if (aspect > m_maxAspect) continue;
                    if (fill   < m_minFill  ) continue;
                    if (fill   > m_maxFill  ) continue;

                    //TODO - draw a red box around the bounding box.

                    if (good_particle >= 0) {
                        // We already found one that could be the goal, so give up.
                        good_particle = -2;
                        break;
                    } else {
                        good_particle = iprt;
                        good_left     = left;
                        good_right    = right;
                    }
                }

		//Send masked image to dashboard to assist in tweaking mask.
		//CameraServer.getInstance().setImage(m_binaryFrame);

                SmartDashboard.putNumber("Good Particle", good_particle);
                

                if (good_particle < 0)
                {
                    m_weHaveAShot = false;
                    m_sideRatio   = -1.0;
                    m_foundTarget = false;
                }
                else if (good_left < 1)
                {
                    m_weHaveAShot = false;
                    m_sideRatio   = 1.0;
                    m_foundTarget = true;
                }
                else
                {
                	m_foundTarget = true;
                	
                    /*NIVision.GetImageSizeResult frame_size = NIVision.imaqGetImageSize(m_binaryFrame);
                    m_sideRatio = (frame_size.width - good_right) / good_left;
                    if (m_sideRatio < m_minSideRatio || m_sideRatio > m_maxSideRatio)
                    {
                        m_weHaveAShot = false;
                    }
                    else
                    {
                        m_weHaveAShot = true;
                    }*/
                    //m_shotAngleError = m_desiredLeftGoalAlignment - good_left;
                	
                	
                	double centerline = (good_right + good_left) / 2;
                	
                	m_shotAngleError = m_desiredCenterLine - centerline;
                	
                	SmartDashboard.putNumber ("Center Line",    centerline);
                	if (Math.abs(centerline - m_desiredCenterLine) <= m_leftGoalAlignmentTolerance)
                	{
                		m_weHaveAShot = true;
                	}
                	else
                	{
                		m_weHaveAShot = false;
                		if (centerline > m_desiredCenterLine)
                		{
                			m_sideRatio = 0.5;
                		}
                		else
                		{
                			m_sideRatio = 2.0;
                		}
                	}
                    
                    if (!m_weHaveAShot)
                    {
                    	//turn
                    	Bling.sendData((byte)37);
                        SmartDashboard.putBoolean("We Need to Turn", true);
                    	
                    	//new AutomaticAim(m_shotAngleError);
                    }
                    else
                    {
                    	m_weHaveAShot = true;
                        SmartDashboard.putBoolean("We Need to Turn", false);
                    }
                }
                SmartDashboard.putBoolean("We Have A Shot", m_weHaveAShot);
                SmartDashboard.putNumber ("Side Ratio",     m_sideRatio);
                SmartDashboard.putNumber ("Angle Error",     m_shotAngleError);
                SmartDashboard.putNumber ("Good Left",     good_left);
                
	}
}
