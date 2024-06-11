public class FixedArrayMap implements Map{  
   // realizzata con array non ordinato di dimensione fissata
   protected Pair[] p;
   protected int pSize;
   private static final int CAPACITY = 5;
   
   // costruttore
   public FixedArrayMap(){	
   	   p = new Pair[CAPACITY];
	   makeEmpty();
	}
	
	// metodi di Container
	public void makeEmpty(){ 
	   pSize = 0; 
	}  
	public boolean isEmpty(){ 
	   return (pSize == 0);
	}
	
	// metodi di accesso
	
	// restituisce il numero di elementi nella mappa
	public int size(){	
	   return pSize;
	}
	
	// restituisce un array con le chiavi presenti nella mappa
	public Object[] keys(){
		Object[] keys = new Object[pSize];
		for(int i=0; i< pSize; i++){
			keys[i] = p[i].getKey();
		}	
		return keys;
	}
	
	// restituisce il valore associato alla chiave key
	// restituisce null se la chiave non c'e'
	public Object get(Object key){ 
      for (int i = 0; i < pSize; i++){
         if ((p[i].getKey()).equals(key)){ 
            return p[i].getValue();
         }
      }   
      return null; // non c’e’
	}

	
	// metodi modificatori
	
	// Inserisce una nuova associazione
	// Se la chiave era gia' presente, sostituisce il 
	// valore associato e restituisce quello vecchio
	public Object put(Object key, Object value){  
		if (key == null || value == null){
         throw new IllegalArgumentException();
        }
		// elimino eventuale Pair esistente con stessa chiave
		Object old = remove(key);
		// se necessario ridimensiono
		if (pSize == p.length){ 
		   throw new FullMapException();
		}   
        // inserisco nuova coppia in fondo e inc. pSize
		p[pSize++] = new Pair(key, value);
		return old;
	}
	
	// rimuove l'associazione con chiave key
	// mantiene l'ordine di inserimento
	public Object remove(Object key){  
		for (int i = 0; i < pSize; i++){  
			if (p[i].getKey().equals(key)){  
				Object obj = p[i].getValue();				
				for(int j=i; j<pSize-1; j++){
					p[i] = p[i+1];
				}	
				pSize--; 
				return obj; 
			}
		}
      return null; // non c’e’
	}

   	// stampa in uscita il contenuto della mappa
    public String toString(){
    	String s = "";
		for(int i=0; i<pSize; i++){
			s += p[i].toString()+"\n";
		}
		return s;
    }
	
	
	// classe interna alla classe FixedArrayMap  
	protected class Pair{ 
      private Object key;
      private Object value;
      
      public Pair(Object k, Object v){  
         setKey(k); 
         setValue(v); 
      }
   
      public Object getKey(){  
         return key;
      }
      public Object getValue(){  
         return value;
      }
      public void setKey(Object k){  
         key = k;
      }
      public void setValue(Object v){
         value = v;
      }
      
      public String toString(){
      	  return key+": "+value;
      }
      
   }
}

class FullMapException extends RuntimeException{}

interface Map extends Container{
   Object put(Object key, Object value);
   Object get(Object key);
   Object remove(Object key);
   Object[] keys();
}

interface Container{
   boolean isEmpty();
   void makeEmpty();

}

