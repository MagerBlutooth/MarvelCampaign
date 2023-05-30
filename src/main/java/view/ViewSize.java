package view;

public enum ViewSize {
    TINY(80), SMALL(100), MEDIUM(150), LARGE(200);

    int viewSize;
    ViewSize(int size)
    {
        viewSize = size;
    }

    public int getSizeVal() {
        return viewSize;
    }
}
