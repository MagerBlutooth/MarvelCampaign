package snapMain.view.grabber;

import snapMain.view.IconImage;
import snapMain.view.grabber.ImageGrabber;

public enum IconConstant {
    STAR {
        @Override
        public IconImage grabImage() {
            ImageGrabber imageGrabber = new ImageGrabber();
            return imageGrabber.grabStarImage();
        }
    }, WOUND {
        @Override
        public IconImage grabImage() {
            ImageGrabber imageGrabber = new ImageGrabber();
            return imageGrabber.grabWoundImage();
        }
    }, ELIMINATE {
        @Override
        public IconImage grabImage() {
            ImageGrabber imageGrabber = new ImageGrabber();
            return imageGrabber.grabEliminateImage();
        }
    }, CAPTURE {
        @Override
        public IconImage grabImage() {
            ImageGrabber imageGrabber = new ImageGrabber();
            return imageGrabber.grabCaptureImage();
        }
    }, SEND_AWAY {
        @Override
        public IconImage grabImage() {
            ImageGrabber imageGrabber = new ImageGrabber();
            return imageGrabber.grabMIAImage();
        }
    }, TEAM {
        @Override
        public IconImage grabImage() {
            ImageGrabber imageGrabber = new ImageGrabber();
            return imageGrabber.grabToTeamImage();
        }
    },TEMP {
        @Override
        public IconImage grabImage() {
            ImageGrabber imageGrabber = new ImageGrabber();
            return imageGrabber.grabToTempImage();
        }
    },  DEFECT {
        @Override
        public IconImage grabImage() {
            ImageGrabber imageGrabber = new ImageGrabber();
            return imageGrabber.grabDefectImage();
        }
    }, BOSS {
        @Override
        public IconImage grabImage() {
            ImageGrabber imageGrabber = new ImageGrabber();
            return imageGrabber.grabBossImage();
        }
    };

    public abstract IconImage grabImage();
}

