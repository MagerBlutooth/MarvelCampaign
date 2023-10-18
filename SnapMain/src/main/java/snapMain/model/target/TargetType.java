package snapMain.model.target;

import snapMain.model.sortFilter.*;

import java.util.ArrayList;
import java.util.List;

public enum TargetType {
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
            return new ArrayList<>();
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
    }, HALL_OF_FAME {
        @Override
        public List<String> getSortOptions() {
            List<String> options = new ArrayList<>();
            for (HallOfFameSortMode mode : HallOfFameSortMode.values())
                options.add(mode.toString());
            return options;
        }

        @Override
        public List<String> getFilterOptions() {
            return new ArrayList<>();
        }
    },
    ADV_CARD {
        @Override
        public List<String> getSortOptions() {
            List<String> options = new ArrayList<>();
            for (BossSortMode mode : BossSortMode.values())
                options.add(mode.toString());
            return options;
        }

        @Override
        public List<String> getFilterOptions() {
            return new ArrayList<>();
        }
    },
    CARD_OR_TOKEN {
        @Override
        public List<String> getSortOptions() {
            List<String> options = new ArrayList<>();
            for (CardSortMode mode : CardSortMode.values())
                options.add(mode.toString());
            return options;
        }

        @Override
        public List<String> getFilterOptions() {
            return new ArrayList<>();
        }
    }, ADV_TOKEN {
        @Override
        public List<String> getSortOptions() {
            List<String> options = new ArrayList<>();
            for (TokenSortMode mode : TokenSortMode.values())
                options.add(mode.toString());
            return options;
        }

        @Override
        public List<String> getFilterOptions() {
            return new ArrayList<>();
        }
    };

    public abstract List<String> getSortOptions();

    public abstract List<String> getFilterOptions();

}