package cocofraccionesrealpro;

/**
 * Esta clase maneja todo lo relacionado 
 * a ingresar una fraccion por string
 * convertirla, hacer operaciones y devolver
 * el string resultado.
 * Algunos métodos son bastantes extraños,
 * lo siento por eso.
 * @author ivxn
 */

public class Fraction 
{
    int[] op1, op2,op3;
    public static String[] numeros_sN;
    public static int[] numeros_iN ;
    private int operation, partsOp1, partsOp2;
    public String respuesta;
    private String[] parts;
    private int partsCount;
    public static boolean error;
    public String s;
    private int[] decEq;
    private String[] dec;
    private String[] statics;
    /**
     * Inicializa los strings constantes
     */
    private void init()
    {
        op3 = new int[]{0,0};
        numeros_sN = new String[]{"cero","un","dos","tres","cuatro","cinco","seis",
                                "siete","ocho","nueve","diez","once","doce",
                                "trece","catorce","quince","dieciseis","diecisiete","dieciocho","diecinueve","veinte",
                                "veintiun","veintidos","veintitres","veinticuatro","veinticinco","veintiseis","veintisiete",
                                "veintiocho","veintinueve","treinta","cuarenta","cincuenta","sesenta","setenta",
                                "ochenta","noventa","cien"};
        numeros_iN = new int[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,
                            22,23,24,25,26,27,28,29,30,40,50,60,70,80,90,100};
        dec = new String[]{"treinta","cuarenta","cincuenta","sesenta","setenta","ochenta","noventa"};
        decEq = new int[] {30,40,50,60,70,80,90};
        statics = new String[]{"cero","entero","medio","tercio","cuarto","quinto","sexto","septimo","octavo","noveno","decimo"};
    }
    
    /**
     * Ruinas de un debugger extraño.
     * Se usa para imrprimir un numero n 
     * en letra, y su respectivo denominador
     * @param n numero a imprimir en letra
     */
    Fraction(int n)
    {
        init();
        System.out.println(getStNumber(n));
        System.out.println(getStFraction(n));
    }
    
    /**
     * Constructor que lee la operación
     * @param _operation String de la operacion a convertir
     */
    Fraction(String _operation)
    {
        init();
        s = _operation;
        parts = s.split(" ");
        partsCount = parts.length;        
        for (int i = 0; i < partsCount; i++) 
        {
            if(isOperation(parts[i]))
            {
                partsOp1 = i;
                partsOp2 = partsCount - i-1;
                break;
            }
        }
        op1 = readOperation(0,partsOp1);
        op2 = readOperation(partsOp1+1, partsCount);
    }
    
    /**
     * Calcula los valores que se tienen en la clase y retorna resultado
     * @return  String del resultado
     */
    public String calculate()
    {
        if(error){
            return "Ajajaj, hay error en tu input, chavo";
        }
        int num, den;
        switch(operation)
        {
            case 0:
                num = op1[0]*op2[1] + op1[1]*op2[0];
                den = op1[1]*op2[1];
                break;
            case 1:
                num = op1[0]*op2[1] + op1[1]*op2[0];
                den = op1[1]*op2[1];
                break;
            case 2:
                num = op1[0]*op2[0];
                den = op1[1]*op2[1];
                break;
            case 3:
                num = op1[0]*op2[1];
                den = op1[1]*op2[0];
                break;
            default:
                num=0;
                den=0;
                break;
        }
        op3[0] = num;
        op3[1] = den;
        String numerador = getStNumber(num);
        String denominador = getStFraction(den);
        return numerador+" "+denominador;
    }
    
    /**
     * Obtiene el string de un numero
     * @param _n el numero a convertir
     * @return  Entrega el string
     */
    private String getStNumber(int _n)
    {
        if(_n==1)
        {
            return "un";
        }
        String ans="";
        int mil = _n/1000;
        _n=_n%1000;
        int cent = _n/100;
        _n = _n%100;
        if(mil>0)
        {
            String sDecMil="";
            if(mil!=1)
            {
                sDecMil = getUnit(mil);
            }
            ans = sDecMil+"mil ";
        }
        if(cent>0)
        {
            String sCent="";
            if(cent==1)
            {
                if(_n==0)
                {
                    sCent="cien ";
                }
                else
                {
                    sCent="ciento ";
                }
            }
            else if(cent==5)
            {
                sCent="quinientos ";
            }
            else if(cent==7)
            {
                sCent="setecientos ";
            }
            else if(cent==9)
            {
                sCent = "novecientos ";
            }
            else if(cent>1)
            {
                sCent = getUnit(cent)+"cientos ";
            }
            ans+=sCent;
        }
        if(_n<=30)
        {
            ans+=getUnit(_n);
        }
        else
        {
            ans+=getStDecimal(_n/10);
            if(_n%10!=0)
            {
                ans+=" y ";
                ans+=getUnit(_n%10);
            }
        }
        return ans;
    }
    
    /**
     * Obtiene el string de una unidad
     * @param _n la unidad
     * @return  el string
     */
    private String getUnit(int _n)
    {
        if(_n==0)
        {
            return "";
        }
        else if(_n==1)
        {
            return "uno";
        }
        else
        {
           return numeros_sN[_n]; 
        }
    }
    
    /**
     * Deveulve el string de la parte decimal
     * @param _n el numero (1 devuelve "diez")
     * @return  el string
     */
    private String getStDecimal(int _n)
    {
        return dec[_n-3];
    }
    
    /**
     * Obtiene el string de una fraccion
     * @param _n la fraccion
     * @return  el string
     */
    private String getStFraction(int _n)
    {
        if(_n<=10||_n==100||_n==1000||_n==10000)
        {
            return op3[0]==1? specialCases(_n): specialCases(_n)+"s";
        }
        String ans="";
        int mil = _n/1000;
        _n=_n%1000;
        int cent = _n/100;
        _n = _n%100;
        if(mil>0)
        {
            String sDecMil="";
            if(mil!=1)
            {
                sDecMil = getUnit(mil);
            }
            ans = sDecMil+"mil";
        }
        if(cent>0)
        {
            String sCent="";
            if(cent==1)
            {
                if(_n==0)
                {
                    sCent="cien";
                }
                else
                {
                    sCent="ciento";
                }
            }
            else if(cent==5)
            {
                sCent="quinientos";
            }
            else if(cent==7)
            {
                sCent="setecientos";
            }
            else if(cent==9)
            {
                sCent = "novecientos";
            }
            else if(cent>1)
            {
                sCent = getUnit(cent)+"cientos";
            }
            ans+=sCent;
        }
        if(_n<=30)
        {
            ans+=getUnit(_n);
        }
        else
        {
            ans+=getStDecimal(_n/10);
            if(_n%10!=0)
            {
                ans+="y";
                ans+=getUnit(_n%10);
            }
        }
        return op3[0]==1? ans+"avo": ans+"avos";
    }
    
    /**
     * Devuelve el string de las fracciones de casos especiales
     * @param i el numero
     * @return  el string
     */
    private String specialCases(int i)
    {
        if(i==100)
        {
            return "centesimo";
        }
        if(i==1000)
        {
            return "milesimo";
        }
        else if(i==10000)
        {
            return "diezmilesimo";
        }
        return statics[i];
    }
    
    /**
     * Lee la fracción entre un rango de las partes de la operación completa
     * @param a Index del inicio de la fracción
     * @param b Index del final de la fracción
     * @return Array con divisor y dividendo
     */
    private int[] readOperation(int a, int b)
    {
        int[] op = new int[2];
        if((b-a)==2)
        {
            op[0] = getSingleNumber(parts[a]);
        }
        else if(parts[a+1].equals("y"))
        {
            op[0] = getDecimalPart(parts[a]) + getSingleNumber(parts[a+2]);
        }
        else
        {
            error=true;
        }
        op[1] = getDenominator(parts[b-1]);
        return op;
    }
    
    /**
     * Retorna el entero del denominador después de comprobar cuál es
     * @param _s String a comparar
     * @return  El entero equivalente
     **/
    private int getDenominator(String _s)
    {
        String[] denominators = new String[101];
        for (int i = 0; i <= 10; i++) 
        {
            denominators[i] = statics[i];
        }
        for (int i = 11; i <= 30; i++) 
        {    
            denominators[i] = numeros_sN[i]+"avo";

        }
        for (int i = 31; i <=99; i++) 
        {
            denominators[i] = getMoreDenominators(i);
        }
        denominators[100] = "centesimo";
        for (int i = 0; i <= 100; i++) 
        {
            if(_s.equals(denominators[i])||_s.equals(denominators[i]+"s"))
            {
                return i;
            }
        }
        error = true;
        return -1;
    }
    
    /**
     * Genera los strins de fracción para 31-99
     * @param i entero a convertir a string
     * @return el string del denominador
     **/
    private String getMoreDenominators(int i)
    {
        int decimalPart = i/10;
        int integerPart = i%10;
        String decPart = numeros_sN[30+decimalPart-3];
        String intPart;
        if(integerPart!=0)
        {
            intPart = "y"+numeros_sN[integerPart];
        }
        else
        {
            intPart="";
        }
        String res = decPart+intPart+"avo";
        return res;
    }
    
    /**
     * Obtiene la parte decimal de un numero de mas de una palabra
     * @param _s String a comparar
     * @return El equivalente en entero
     */
    private int getDecimalPart(String _s)
    {
        for(int i=0;i<dec.length; i++)
        {
            if(_s.equals(dec[i]))
            {
                return decEq[i];
            }
        }
        System.out.println("Error mandando numero decimal");
        error=true;
        return -100;
  
    }
    
    /**
     * Retorna el numero de un string con una sola palabra (0-30)
     * @param s String a comparar
     * @return El numero en entero
     */
    private int getSingleNumber(String s)
    {  
        for (int i = 0; i < numeros_sN.length; i++) 
        {
            if(s.equals(numeros_sN[i]))
            {
                return numeros_iN[i];
            }
        }
        System.out.println("Error obteniendo single number");
        error =true;
        return -100;
    }
    
    /**
     * Comprueba si el string es una operacion 
     * @param s String a comparar
     * @return el ID para la operación a realizar.
     */
    private boolean isOperation(String s)
    {
        String operations[] = {"mas","menos","por","entre"};
        for (int i = 0; i < 4; i++) 
        {   
            if(s.equals(operations[i]))
            {
                operation = i;
                return true;
            }
        }
        return false;
    }
}