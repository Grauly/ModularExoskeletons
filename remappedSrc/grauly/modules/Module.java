package grauly.modules;

public interface Module {
    int getSlotCost();
    int getUpkeepCost();
    int getUseCost();
    String getDescription();
    void tick();
}
