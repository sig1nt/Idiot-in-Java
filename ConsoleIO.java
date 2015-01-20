//Handles the IO with terminal/cmd
/*USAGE
  Constructors
  ConsoleIO() constructs a ConsoleIO object with the error message of "Does not work", 125 milliseconds between letters, 80 characters per line and 2 seconds wait in between lines
  ConsoleIO(String m) constructs a ConsoleIO object with the error message m, 125 milliseconds between letters, 80 characters per line and 2 seconds wait in between lines
  ConsoleIO(String m, int ts) constructs a ConsoleIO object with the error message m, ts milliseconds between letters, 80 characters per line and 2 seconds wait in between lines
  ConsoleIO(String m, int ts, int br) constructs a ConsoleIO object with the error message m, ts milliseconds between letters, br characters per line and 2 seconds wait in between lines
  ConsoleIO(String m, int ts, int br, int wait) constructs a ConsoleIO object with the error message m, ts milliseconds between letters, br characters per line and wait milliseconds wait in between lines
  
  Methods
  in(String ask, Object o) Outputs the string ask then checks to see if the token in the buffer can be read as an object with the same type as o
  typeln(Object... out) types out each Object's toString value in out[] on it's own line
  type(Object... out) types out each Object's toString value in out[] on one line
  l() goes to a new line
*/  

import java.util.Scanner;
import java.math.*;

public class ConsoleIO{
	private String message = "";
	private int typespeed = 0, length = 0, lineWait = 0;
  Scanner s = new Scanner(System.in);
	
	public ConsoleIO(){
		this("Input does not work!", 125, 80, 2000);
	}
	
	public ConsoleIO(String m){
		this(m, 125, 80, 2000);
	}
	
	public ConsoleIO(String m, int ts){
		this(m, ts, 80, 2000);
	}
	
	public ConsoleIO(String m, int ts, int br){
		this(m, ts, br, 2000);
	}
	
	public ConsoleIO(int ts, int br, int wait){
		this("Input does not work!", ts, br, wait);
	}
	
	public ConsoleIO(String m, int ts, int br, int wait){
		message = m;
		typespeed = ts;
		length = br;
		lineWait = wait;
	}
	
	public void setMsg(String m){
		message = m;
	}
	
	public void setSpd(int ts){
		typespeed = ts;
	}
	public void setLength(int br){
		length = br;
	}
	
	public void setLineWait(int wait){
		lineWait = wait;
	}
	
	public Object in(String ask, Object o){
		type(ask);
		while(s.hasNext()){
			if(o instanceof BigDecimal && s.hasNextBigDecimal()){
				return s.nextBigDecimal();
			}else if(o instanceof BigInteger && s.hasNextBigInteger()){
				return s.nextBigInteger();
			}else if(o instanceof Boolean && s.hasNextBoolean()){
				boolean b = (boolean)s.nextBoolean();
        s.nextLine();
        return b;
			}else if(o instanceof Byte && s.hasNextByte()){
        byte b = (byte)s.nextByte();
        s.nextLine();
        return b;
			}else if(o instanceof Character){
				return s.nextLine().charAt(0);
			}else if(o instanceof Double && s.hasNextDouble()){
        double d = (double)s.nextDouble();
        s.nextLine();
        return d;
			}else if(o instanceof Float && s.hasNextFloat()){
				float f = (float)s.nextFloat();
        s.nextLine();
        return f;
			}else if(o instanceof Integer && s.hasNextInt()){
				int i = (int)s.nextInt();
        s.nextLine();
        return i;
			}else if(o instanceof String){
				return s.nextLine();
			}else if(o instanceof Long && s.hasNextLong()){
        long l = s.nextLong();
        s.nextLine();
        return l;
			}else if(o instanceof Short && s.hasNextShort()){
        short sh = s.nextShort();
        s.nextLine();
        return sh;
			}else{
				typeln(s.nextLine() + " is not an instance of " + o.getClass().getSimpleName(), ask);
			}
		}
		return null;
	}
	
	public void typeln(Object... out){
		for(Object line: out){
			type(line);
			try{
				Thread.sleep(lineWait);
			}catch(Exception e){
				type("Oops");
			}
      l();
		}
		l();
	}
	
	public void type(Object... out){
		for(Object l: out){
			String line = l.toString(), letter = "", output = "", words[] = (line.replace("\n", " \n ")).split(" ");
			int j = 0;
			for(String word:words){
				if(j + word.length() > length && !word.equals("\n")){
					output += "\n" + word + " ";
					j = word.length() - 1;
				}else if(word.equals("\n")){
					output += "\n";
					j = 0;
				}else{
					output += word + " ";
					j += 1 + word.length();
				}
			}
			for(int i = 0; i < output.length(); i++){
				if(i != output.length()){
					letter = output.substring(i, i + 1);
				}else{
					letter = output.substring(i);
				}
				System.out.print(letter);
				try{
					Thread.sleep(typespeed);
				}catch(Exception e){
					type("Oops");
				}
			}
			
		}
	}
	
	public void l(){
		System.out.println();
	}
}
