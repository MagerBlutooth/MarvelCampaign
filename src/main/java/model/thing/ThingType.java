package model.thing;

import model.constants.CampaignConstants;
import model.sortFilter.CardSortMode;
import model.sortFilter.LocationSortMode;
import model.sortFilter.TokenSortMode;

import java.util.ArrayList;
import java.util.List;

public enum ThingType {
    CARD {
        public List<String> getSortOptions() {
            List<String> options = new ArrayList<>();
            for (CardSortMode mode : CardSortMode.values()) {
                options.add(mode.toString());
            }
            return options;
        }

        public List<String> getFilterOptions() {
            List<String> options = new ArrayList<>();
            for (CardAttribute mode : CardAttribute.values()) {
                options.add(mode.toString());
            }
            return options;
        }
    }, LOCATION {
        public List<String> getSortOptions() {
            List<String> options = new ArrayList<>();
            for (LocationSortMode mode : LocationSortMode.values()) {
                options.add(mode.toString());
            }
            return options;
        }

        @Override
        public List<String> getFilterOptions() {
            List<String> options = new ArrayList<>();
            for (LocationSortMode mode : LocationSortMode.values()) {
                options.add(mode.toString());
            }
            return options;
        }

    }, TOKEN {
        public List<String> getSortOptions() {
            List<String> options = new ArrayList<>();
            for (TokenSortMode mode : TokenSortMode.values()) {
                options.add(mode.toString());
            }
            return options;
        }

        public Token getThing()
        {
            return new Token();
        }

        @Override
        public List<String> getFilterOptions() {
            return new ArrayList<>();
        }
    };

    public abstract List<String> getSortOptions();

    public abstract List<String> getFilterOptions();

}