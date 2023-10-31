package snapMain.model.sortFilter;

import snapMain.model.target.Token;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TokenSorter {

    TokenSortMode tokenSortMode;

    public TokenSorter()
    {
        tokenSortMode = TokenSortMode.NAME;
    }

    public List<Token> sort(List<Token> tokens)
    {
        List<Token> sortedCards = new ArrayList<>(tokens);
        switch(tokenSortMode)
        {
            case NAME:
                sortedCards.sort((o1, o2) -> Comparator.comparing(Token::getName).compare(o1, o2));
                break;
        }
        return sortedCards;
    }

    public void changeMode(TokenSortMode c)
    {
        tokenSortMode = c;
    }
}
