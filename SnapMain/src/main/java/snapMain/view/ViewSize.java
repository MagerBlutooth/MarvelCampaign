package snapMain.view;

public enum ViewSize {
    TINY(70), SMALL(100), MEDIUM(150), LARGE(200);

    int viewSize;
    ViewSize(int size)
    {
        viewSize = size;
    }

    public int getSizeVal() {
        return viewSize;
    }
}
