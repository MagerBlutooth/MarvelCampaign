package model.thing;

import java.util.List;

public class TokenList<T extends Thing> extends ThingList<Token> {

    public TokenList(List<Token> t) {
        super(t);
    }

    @Override
    public void sort() {

    }

    @Override
    public void setSortMode(String m) {

    }
}
