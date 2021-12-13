package remora.remora.Common;

import org.jcodec.common.model.Picture;

public class PicturePair {
    int pictureNo;
    Picture picture;

    public PicturePair(int pictureNo, Picture picture) {
        this.pictureNo = pictureNo;
        this.picture = picture;
    }

    public int first() {
        return pictureNo;
    }

    public Picture second() {
        return picture;
    }
}
