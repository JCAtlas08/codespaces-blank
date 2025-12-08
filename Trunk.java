//this goes into it's own file
class Trunk {
    Trunk prev;
    String str;
 
    Trunk(Trunk prev, String str) {
        this.prev = prev;
        this.str = str;
    }
};