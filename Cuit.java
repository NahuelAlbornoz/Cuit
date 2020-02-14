import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;

public class Cuit 
{
	public static void main(String[] args) 
	{
		Scanner abrir=null;  //inicializo objeto
		PrintWriter escribir=null;
		
		try
		{
			abrir = new Scanner(new File("cuit-in.txt"));//Invoco al constructor de la clase Scanner y le envio como parametro un objeto de tipo file
			escribir = new PrintWriter(new File("cuit-out.txt"));
			
			String cuit;
			
			while(abrir.hasNextLine()==true)//Mientras se encuentren mas lineas para leer en el archivo.. 
			{
				cuit=abrir.nextLine();
				int [] arreglo_cuit = new int [11];
				int [] cuit_original = new int [11];
				int indice;
				
				for(indice=0;indice<cuit.length();indice++)
				{
					char num=cuit.charAt(indice);
					String cad=num+"";
					int numero=Integer.parseInt(cad);
					cuit_original[indice]=numero;
					arreglo_cuit[indice]=numero;
				}
				
				int [] sum_cuit = new int [10];
				int acum=0;
				
				for(indice=0;indice<10;indice++)
				{	
					if((indice==0)||(indice==6))
						sum_cuit[indice]=arreglo_cuit[indice]*5;
					else if((indice==1)||(indice==7))
						sum_cuit[indice]=arreglo_cuit[indice]*4;
					else if((indice==2)||(indice==8))
						sum_cuit[indice]=arreglo_cuit[indice]*3;
					else if((indice==3)||(indice==9))
						sum_cuit[indice]=arreglo_cuit[indice]*2;
					else if(indice==4)
						sum_cuit[indice]=arreglo_cuit[indice]*7;
					else if(indice==5)
						sum_cuit[indice]=arreglo_cuit[indice]*6;
					
					acum=acum+sum_cuit[indice];
				}
				
				boolean estado=true;
				int resto=acum%11;
				
				if(resto==0)
				{
					if(arreglo_cuit[10]!=resto)
					{
						estado=false;
						arreglo_cuit[10]=0;
					}
				}	
				else if(resto==1)
				{
					if((arreglo_cuit[0]==2)&&(arreglo_cuit[1]==0))//si es hombre
					{
						arreglo_cuit[1]=3;
						arreglo_cuit[10]=9;
					}
					else if((arreglo_cuit[0]==2)&&(arreglo_cuit[1]==7))//si es mujer
					{
						arreglo_cuit[1]=3;
						arreglo_cuit[10]=4;
					}
					
					estado=false;
				}
				else if((resto!=1)&&(resto!=0))
				{
					int cod_verif=11-resto;
					if(arreglo_cuit[10]!=cod_verif)
					{
						arreglo_cuit[10]=cod_verif;	
						estado=false;
					}
				}
				
				for(indice=0;indice<arreglo_cuit.length;indice++)
					escribir.print(cuit_original[indice]);
				
				escribir.printf("\t\t"+estado);
				
				if(estado==false)
				{
					escribir.printf("\t\tCorrecto: ");
					for(indice=0;indice<arreglo_cuit.length;indice++)
						escribir.print(arreglo_cuit[indice]);
				}
				escribir.println();
			}
		}
		
		catch(Exception FileNotFoundException)
		{
			System.out.println("Error al intentar abrir el archivo");
		}
		
		finally
		{
			abrir.close();
			escribir.close();
			System.out.println("Se han guardado los datos correctamente..");
			System.out.println("Fin del programa");
		}
	}
}