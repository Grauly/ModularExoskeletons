package grauly.modules.base;

public interface Module {
    int getSlotCost();
    int getUpkeepCost();
    int getUseCost();
    String getDescription();
    void tick();
}
