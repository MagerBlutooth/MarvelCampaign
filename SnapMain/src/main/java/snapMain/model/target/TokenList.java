package snapMain.model.target;

import snapMain.model.sortFilter.TokenSortMode;
import snapMain.model.sortFilter.TokenSorter;

import java.util.ArrayList;
import java.util.List;

public class TokenList extends TargetList<Token> {

    TokenSortMode sortMode;
    TokenSorter tokenSorter;

    public TokenList(List<Token> t) {
        super(t);
        tokenSorter = new TokenSorter();
    }

    @Override
    public void sort() {
        List<Token> sortedCards = tokenSorter.sort(new ArrayList<>(getThings()));
        this.clear();
        this.addAll(sortedCards);
    }

    @Override
    public void setSortMode(String m) {
        sortMode = TokenSortMode.parseString(m);
    }
}
