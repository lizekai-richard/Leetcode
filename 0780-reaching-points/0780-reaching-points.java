class Solution {
    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        if (tx < sx || ty < sy) {
            return false;
        }
        while (tx > sx && ty > sy) {
            if (tx >= ty) {
                tx = tx % ty;
            } else {
                ty = ty % tx;
            }
        }
        if (tx != sx && ty != sy) {
            return false;
        } else if (tx == sx) {
            return (ty - sy) % tx == 0;
        } else if (ty == sy) {
            return (tx - sx) % ty == 0;
        } else {
            return true;
        }
    }
}