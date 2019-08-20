package domain;

public abstract interface ICombat
{
  public abstract void attack(NPC npc);
  
  public abstract int calcDMG();
  
  public abstract void takeDMG(int amount);
  
  public abstract int calcRES();
}