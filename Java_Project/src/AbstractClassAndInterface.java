abstract class Device {
    String brand = "Samsung";
    abstract void turnOn();
    void showBrand(){
        System.out.println("Brand: "+brand);
    }
}
interface Camera {
    int MAX_ZOOM = 10;
    void takephoto();
    default void camerainfo(){
        System.out.println("Camera is ready");
        
    }
}

interface MusicPlayer {
    String TYPE="Digital";
    void playMusic();
    default void musicInfo(){
        System.out.println("Music Player is ready");
    }
}

class Smartphone extends Device implements Camera, MusicPlayer {
    public void turnOn(){
        System.out.println("Smartphone is turned on");
    }
    public void takephoto(){
        System.out.println("Taking photo....");
    }
    public void playMusic(){
        System.out.println("Playing music....");
    }
}

public class AbstractClassAndInterface {
    public static void main(String[] args) {
        Smartphone obj = new Smartphone();
        obj.turnOn();
        obj.showBrand();
        obj.takephoto();
        obj.playMusic();
        obj.camerainfo();
        obj.musicInfo();
    }
}