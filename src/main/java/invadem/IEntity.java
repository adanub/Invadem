package invadem;

public interface IEntity extends IHittable {

    enum Type {
        TANK, INVADER, BULLET, BARRIER
    }

    public Vector2 GetPos();

    public Type GetEntityType();

    public Vector2 GetSize();

    public void Render();

    public void Update();
}
