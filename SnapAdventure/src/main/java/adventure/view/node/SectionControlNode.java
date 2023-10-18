package adventure.view.node;

import adventure.model.target.Section;
import snapMain.controller.MainDatabase;
import snapMain.model.target.TargetType;
import snapMain.view.IconImage;
import snapMain.view.ViewSize;
import snapMain.view.node.control.ControlNode;

public class SectionControlNode extends ControlNode<Section> {

    @Override
        public void initialize(MainDatabase db, Section s, IconImage i, ViewSize v, boolean revealed) {
            mainDatabase = db;
            targetType = TargetType.LOCATION;
            subject = s;
            imageView.setImage(i);
            imageView.setFitWidth(v.getSizeVal());
            imageView.setFitHeight(v.getSizeVal());
            if(!s.isRevealed())
                unreveal();
            if(s.isCompleted())
                complete();
            else
                incomplete();
        }

    public void unreveal() {
        imageView.setImage(mainDatabase.grabBlankImage(TargetType.LOCATION));
    }

    private void complete()
    {
        lowlight();
    }

    private void incomplete() {
        highlight();
    }


    public void reveal() {
        imageView.setImage(mainDatabase.grabImage(subject.getLocation()));
    }

    public void refresh(Section s) {
        IconImage i = mainDatabase.grabImage(s.getLocation());
        subject = s;
        imageView.setImage(i);
        if(!subject.isRevealed())
            unreveal();
        else
            reveal();
        if(subject.isCompleted())
            complete();
        else
            incomplete();
    }

    public boolean isRevealed() {
        return subject.isRevealed();
    }
}
