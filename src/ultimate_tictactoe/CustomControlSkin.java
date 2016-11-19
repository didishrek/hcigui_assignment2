package ultimate_tictactoe;

import javafx.scene.control.Skin;
import javafx.scene.control.SkinBase;

/**
 * Created by Adrien Schricke on 15/11/2016.
 */
public class CustomControlSkin extends SkinBase<CustomControl> implements Skin<CustomControl> {
    public CustomControlSkin(CustomControl cc) {
// call the super class constructor
        super(cc);
    }
}
