package invadem;

public interface IHittable {
    public int GetHealthPoints();
    public void Hit(int damage); //Positive damage value corresponds to a reduction in health. Negative value means health is restored.
}
