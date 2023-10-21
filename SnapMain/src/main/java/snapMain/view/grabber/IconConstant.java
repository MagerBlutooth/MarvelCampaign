package snapMain.view.grabber;

import snapMain.view.IconImage;

import java.net.URL;

public enum IconConstant {
    POWER {
        @Override
        String getImageName() {
            return "/powerIcon.png";
        }
    },
    COST {
        @Override
        String getImageName() {
            return "/costIcon.png";
        }
    },
    STAR {
        @Override
        String getImageName() {
            return "/captainStar.png";
        }
    }, HEAL {
        @Override
        String getImageName() {
            return "/healSign.png";
        }
    },
    WOUND {
        @Override
        String getImageName() {
            return "/woundSign.png";
        }
    }, ELIMINATE {
        @Override
        String getImageName() {
            return "/eliminateSign.png";
        }
    }, CAPTURE {
        @Override
        String getImageName() {
            return "/captureIcon.png";
        }
    }, SEND_AWAY {
        @Override
        String getImageName() {
            return "/sendAwayIcon.png";
        }
    }, TEAM {
        @Override
        String getImageName() {
            return "/toTeamIcon.png";
        }
    }, TEMP {
        @Override
        String getImageName() {
            return "/toTempIcon.png";
        }
    }, DEFECT {
        @Override
        String getImageName() {
            return "/defectSign.png";
        }
    }, BOSS {
        @Override
        String getImageName() {
            return "/bossImage.png";
        }
    }, PASTE {
        @Override
        String getImageName() {
            return "/pasteIcon.png";
        }
    }, COPY {
        @Override
        String getImageName() {
            return "/copyIcon.png";
        }
    }, DICE {
        @Override
        String getImageName() {
            return "/diceIcon.png";
        }
    }, CLEAR {
        @Override
        String getImageName() {
            return "/clearIcon.png";
        }
    }, D2 {
        @Override
        String getImageName() {
            return "/d2Icon.png";
        }
    }, D4 {
        @Override
        String getImageName() {
            return "/d4Icon.png";
        }
    },
    D6 {
        @Override
        String getImageName() {
            return "/d6Icon.png";
        }
    }, D8 {
        @Override
        String getImageName() {
            return "/d8Icon.png";
        }
    }, D10 {
        @Override
        String getImageName() {
            return "/d10Icon.png";
        }
    }, D12 {
        @Override
        String getImageName() {
            return "/d12Icon.png";
        }
    }, D20 {
        @Override
        String getImageName() {
            return "/d20Icon.png";
        }
    }, D100 {
        @Override
        String getImageName() {
            return "/d100Icon.png";
        }
    }, EXHAUST {

    @Override
    String getImageName() {
        return "/exhaustSign.png";
    }
    }, RECOVER {
        @Override
        String getImageName() {
            return "/recoverSign.png";
        }
    }, PIG {
        @Override
        String getImageName()  {
            return "/pigIcon.png";
        }
    }, RAPTOR {
        @Override
        String getImageName() {
            return "/raptorIcon.png";
        }
    };

    public IconImage grabImage()
    {
        String bossImageFile = getImageName();
        URL url = getClass().getResource(bossImageFile);
        assert url != null;
        return new IconImage(url.toExternalForm());

    }

    abstract String getImageName();
}

