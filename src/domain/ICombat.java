package domain;

public abstract interface ICombat
{
  public abstract void attack(NPC paramNPC);
  
  public abstract int calcDMG();
  
  public abstract void takeDMG(int paramInt);
  
  public abstract int calcRES();
}