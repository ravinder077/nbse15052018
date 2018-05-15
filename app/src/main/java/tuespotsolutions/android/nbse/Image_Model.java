package tuespotsolutions.android.nbse;

public class Image_Model {

    private int img_id;
    private int image;

    public Image_Model(int img_id, int image) {
        this.img_id = img_id;
        this.image = image;
    }

    public int getImg_id() {
        return img_id;
    }

    public void setImg_id(int img_id) {
        this.img_id = img_id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
