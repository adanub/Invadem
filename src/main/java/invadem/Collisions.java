package invadem;

public final class Collisions {
    private Collisions() {
        //making constructor private so it cannot be instantiated
    }

    public static boolean Check(IEntity e1, IEntity e2) {
        if (
                (e1.GetPos().x - e1.GetSize().x) <= (e2.GetPos().x + e2.GetSize().x) &&
                (e1.GetPos().y - e1.GetSize().y) <= (e2.GetPos().y + e2.GetSize().y)
        ) {
            return true;
        }
        return false;
    }
}
