package assets;

import java.util.List;

/**
 * Created by Ryan on 6/8/2015.
 */
public class Pair<Left, Right> {
    private Left L;
    private Right R;
    public Pair(Left L, Right R) {
        this.L = L;
        this.R = R;
    }
    public Left getL(){ return L; }
    public Right getR(){return R;}
}


