public class GameSingleton{
    private static Game g;
    
    public Game getGame(){
        if(g == null){
            g = new Game();
        }
        return g;
    }
}
