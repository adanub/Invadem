package invadem;

public final class Collisions {
    private Collisions() {
        //making constructor private so it cannot be instantiated
    }

    public static boolean Check(IEntity e1, IEntity e2) {
        if (
                (e1.GetPos().x - e1.GetSize().x/2) <= (e2.GetPos().x + e2.GetSize().x/2)
                && (e2.GetPos().x - e2.GetSize().x/2) <= (e1.GetPos().x + e1.GetSize().x/2)
                && (e1.GetPos().y - e1.GetSize().y/2) <= (e2.GetPos().y + e2.GetSize().y/2)
                && (e2.GetPos().y - e2.GetSize().y/2) <= (e1.GetPos().y + e1.GetSize().y/2)
        ) {
            return true;
        }
        return false;
    }
}
