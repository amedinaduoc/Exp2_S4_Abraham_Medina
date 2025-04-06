import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Teatro_Moro_E2_S4 {

	/**
	 * @param args the command line arguments
	 */

	public static void main(String[] args) {

		Scanner entradaUser = new Scanner(System.in);

		// ************* ASIGNACION DE VARIABLkES
		int opcionInicial = 0, reservado = 0, asientosVendidos = 0, entero = 0, saltoFilas = 1, totalFilas = 1;

		// ************* ASIGNACION DE CONSTANTES
		final int maxAsientos = 30;
		final int maxAsientosFila = maxAsientos / 3;
		final int filaZonaA = 1;
		final int filaZonaB = filaZonaA + maxAsientosFila;
		final int filaZonaC = filaZonaB + maxAsientosFila;
		String asientosDisponibles = "", anteponerCero = "", comprasOld = "", tipoDescuento = "", pocosAsientos = "";

		/************* PROBAR POCAS ENTRADAS
		   comprasOld = "1 3 4 5 8 9 10 12 14 16 17 18 20 22 24 25 26 28 30 ";
		 *************/
		
		// ************* REPETICION DEL CODIGO
		for (;;) {
			asientosVendidos = 0;

			// ************* MOSTRAR OPCIONES INICIALES
			Pattern pattern = Pattern.compile("\\d+");
			Matcher matcher = pattern.matcher(comprasOld);
			while (matcher.find()) {
				entero = Integer.parseInt(matcher.group());
				asientosVendidos++;
			}
			System.out.println("\u001B[36m________________________________________________\u001B[0m");
			if (asientosVendidos >= maxAsientos) {
				System.out.println("\u001B[31m\u001B[47mNO QUEDAN ENTRADAS DISPONIBLES\u001B[0m");
			} else {
				System.out.println("\u001B[33m\u001B[40mSeleccione una de las siguientes opciones:\u001B[0m");
				System.out.println("\u001B[36m1 .- Comprar entrada");
			}
			System.out.println("\u001B[36m2 .- Salir\u001B[0m");

			// ************* LEER LA OPCION INGRESADA Y VALIDAR
			try {
				opcionInicial = entradaUser.nextInt();
				// ************* SI NO QUEDAN ENTRADAS RETORNAR HASTA SALIR
				if (asientosVendidos >= maxAsientos && opcionInicial != 2) {
					opcionInicial = 0;
				}
			} catch (Exception ex) {
				entradaUser.nextLine();
			}

			switch (opcionInicial) {

			// ************* OPCION 1 COMPRAR ENTRADAS
			case 1:
				System.out.println("\n\n\n\n\n");
				boolean validaAsiento = false;
				int numeroAsiento = 0;
				int edadUsuario = 0;
				String zonaAsiento = "";
				int zonaPrecio = 0;
				double descuento = 0;

				// ************* MUESTRA ZONAS ASIENTOS MAPA PRECIOS
				do {
					saltoFilas = 1;
					totalFilas = 1;

					// ************* CALCULAR ASIENTOS DISPONIBLES
					if (maxAsientos - asientosVendidos < 10) {
						asientosDisponibles = "0" + (maxAsientos - asientosVendidos);
						pocosAsientos = "\u001B[37m\u001B[41m";
					} else {
						asientosDisponibles = "" + (maxAsientos - asientosVendidos);
						pocosAsientos = "\u001B[40m";
					}

					// ************* MOSTRAR MAPA DEL TEATRO
					System.out.println("\u001B[36m________________________________________________\u001B[0m");
					System.out.println("\u001B[32mAsientos disponibles: " + pocosAsientos + asientosDisponibles + "\u001B[0m\u001B[36m");
					System.out.println("                   ____________________________");
					System.out.println("                  |          ESCENARIO         |");
					System.out.println("                  |____________________________|\u001B[0m");

					while (saltoFilas <= maxAsientosFila && totalFilas <= maxAsientos) {
						saltoFilas = 1;

						// ************* IMPRIMIR ZONAS
						switch (totalFilas) {
						case filaZonaA:
							System.out.print("\u001B[36mZona A : $43000 : ");
							break;
						case filaZonaB:
							System.out.print("\n\u001B[36mZona B : $34000 : ");
							break;
						case filaZonaC:
							System.out.print("\n\u001B[36mZona C : $19000 : ");
							break;
						}

						// ************* ANTEPONER CERO SI ES MENOR A 10
						if (totalFilas < 10) {
							anteponerCero = "0";
						} else {
							anteponerCero = "";
						}

						// ************* BUSCAR NUMEROS OCUPADOS COMPARAR Y DESTACAR
						matcher = pattern.matcher(comprasOld);
						while (matcher.find()) {
							entero = Integer.parseInt(matcher.group());
							if (totalFilas == entero) {
								reservado = 1;
							}
						}
						if (totalFilas == numeroAsiento || reservado == 1) {
							String colorFondo = (totalFilas == numeroAsiento) ? "\u001B[47m" : "\u001B[43m";
							System.out.print("\u001B[31m" + colorFondo + anteponerCero + totalFilas + "\u001B[0m ");
						} else {
							System.out.print("\u001B[32m" + anteponerCero + totalFilas + " ");
						}
						reservado = 0;
						saltoFilas++;
						totalFilas++;
					}

					// ************* INGRESO Y VALIDACION NUMERO DE ASIENTO
					System.out.print("\n\u001B[33m\u001B[40mIngrese el numero de asiento deseado:\u001B[0m");
					System.out.println("\u001B[0m");
					try {
						numeroAsiento = entradaUser.nextInt();

						// ************* NO PERMITIR UN ASIENTO OCUPADO
						matcher = pattern.matcher(comprasOld);
						while (matcher.find()) {
							entero = Integer.parseInt(matcher.group());
							if (numeroAsiento == entero) {
								numeroAsiento = 0;
							}
						}
					} catch (Exception ex) {
						entradaUser.nextLine();
					}

					// ************* VERIFICAR NUMERO ASIENTO ASIGNAR ZONA PRECIO Y MOSTRAR SELECCION
					if (numeroAsiento >= filaZonaA && numeroAsiento <= maxAsientosFila) {
						zonaAsiento = "Zona: A";
						zonaPrecio = 43000;
						validaAsiento = true;
					} else if (numeroAsiento >= filaZonaB && numeroAsiento < filaZonaC) {
						zonaAsiento = "Zona: B";
						zonaPrecio = 34000;
						validaAsiento = true;
					} else if (numeroAsiento >= filaZonaC && numeroAsiento <= maxAsientos) {
						zonaPrecio = 19000;
						zonaAsiento = "Zona: C";
						validaAsiento = true;
					} else {
						System.out.println(
								"\n\n\n\n\n\u001B[31m\u001B[47mERROR: El asiento ingresado no se encuentra disponible.\u001B[0m");
					}

					// ************* ANTEPONER CERO SI ES MENOR A 10
					if (numeroAsiento < 10) {
						anteponerCero = "0";
					} else {
						anteponerCero = "";
					}
				} while (!validaAsiento);

				// ************* INGRESO VALIDACION Y CATEGORIZAR EDAD
				boolean validaEdad = false;
				System.out.println("\n\n\n\n\n");
				while (!validaEdad) {
					// ************* MOSTRAR SELECCION ACTUAL
						System.out.println("\u001B[36m________________________________________________\u001B[0m");
						System.out.println("\u001B[36m\u001B[40mSU SELECCION ACTUAL ES:\u001B[0m");
						System.out.println("\u001B[36mAsiento: \u001B[31m\u001B[47m" + anteponerCero + numeroAsiento
								+ "\n\u001B[0m\u001B[36m" + zonaAsiento + "\n\u001B[32mSubTotal: \u001B[40m$"
								+ zonaPrecio + "\u001B[0m");

					System.out.println("\n\u001B[33m\u001B[40mIngrese su edad:\u001B[0m");
					try {
						edadUsuario = entradaUser.nextInt();
					} catch (Exception ex) {
						entradaUser.nextLine();
					}
					if (edadUsuario >= 1 && edadUsuario <= 18) {
						descuento = 0.1;
						tipoDescuento = "Estudiante";
						validaEdad = true;
					} else if (edadUsuario >= 19 && edadUsuario <= 59) {
						descuento = 0;
						tipoDescuento = "";
						validaEdad = true;
					} else if (edadUsuario >= 60 && edadUsuario <= 120) {
						descuento = 0.15;
						tipoDescuento = "Tercera edad";
						validaEdad = true;
					} else {
						System.out.println("\n\n\n\n\n\u001B[31m\u001B[47mERROR: La edad ingresada no es valida.\u001B[0m");
					}
				}

				// ************* MOSTRAR RESULTADOS FINALES
				int resumen = 0;

				// ************* SEGUN PAUTA DEBIA AGREGAR UN WHILE A CONTINUACION, NO SE ME
				// OCURRIO DE QUE MANERA!
				System.out.println("\n\n\n\n\n");
				while (resumen == 0) {
					System.out.println("\u001B[36m________________________________________________\u001B[0m");
					System.out.println("\u001B[36m\u001B[40mRESUMEN DE SU COMPRA:\u001B[0m");
					double calculoDescuento = zonaPrecio - (zonaPrecio * descuento);
					double muestraPorcentaje = descuento * 100;
					if (numeroAsiento < maxAsientosFila) {
						anteponerCero = "0" + numeroAsiento;
					} else {
						anteponerCero = "" + numeroAsiento;
					}
					System.out.print("\u001B[36mAsiento: \u001B[31m\u001B[47m" + anteponerCero + "\n\u001B[0m\u001B[36m"
							+ zonaAsiento + "\n\u001B[32mSubTotal: \u001B[40m$" + zonaPrecio
							+ "\n\u001B[0m\u001B[36mEdad: " + edadUsuario + "\n\u001B[32mDcto " + tipoDescuento
							+ ": \u001B[40m");
					System.out.printf("%.0f", muestraPorcentaje);
					System.out.print(" %\n\u001B[0m\u001B[32mTotal a Pagar: \u001B[31m\u001B[43m$");
					System.out.printf("%.0f\n", calculoDescuento);
					resumen++;
				}

				// ************* MUESTRA ASIENTOS OCUPADOS Y ULTIMO COMPRADO
				saltoFilas = 1;
				totalFilas = 1;
				System.out.print("\u001B[0m\u001B[32m");
				System.out.println("          ____________________________");
				System.out.println("         |          ESCENARIO         |");
				System.out.println("         |____________________________|");

				while (saltoFilas <= maxAsientosFila && totalFilas <= maxAsientos) {
					saltoFilas = 1;
					anteponerCero = "";
					// ************* IMPRIMIR ZONAS
					switch (totalFilas) {
					case filaZonaA:
						System.out.print("\u001B[36mZona A : ");
						break;
					case filaZonaB:
						System.out.print("\n\u001B[36mZona B : ");
						break;
					case filaZonaC:
						System.out.print("\n\u001B[36mZona C : ");
						break;
					}

					// ************* ANTEPONER CERO SI ES MENOR A 10
					if (totalFilas < 10) {
						anteponerCero = "0";
					} else {
						anteponerCero = "";
					}

					// ************* BUSCAR NUMEROS OCUPADOS COMPARAR Y MARCAR
					matcher = pattern.matcher(comprasOld);
					while (matcher.find()) {
						entero = Integer.parseInt(matcher.group());
						if (totalFilas == entero) {
							reservado = 1;
						}
					}
					if (totalFilas == numeroAsiento || reservado == 1) {
						String colorFondo = (totalFilas == numeroAsiento) ? "\u001B[47m" : "\u001B[43m";
						System.out.print("\u001B[31m" + colorFondo + anteponerCero + totalFilas + "\u001B[0m ");
					} else {
						System.out.print("\u001B[32m" + anteponerCero + totalFilas + " ");
					}
					reservado = 0;
					saltoFilas++;
					totalFilas++;
				}

				System.out.println("\n");

				// ************* ALMACENAR HISTORIAL DE RESERVAS
				comprasOld = comprasOld + numeroAsiento + " ";
				opcionInicial = 0;
				break;

			// ************* OPCION SALIR DEL PROGRAMA
			case 2:
				System.out.println("\n\n\n\n\n");
				System.out.println("\u001B[32m________________________________________________\u001B[0m");
				if (asientosVendidos > 0) {
				System.out.println("\n\u001B[32mMuchas gracias por su compra.");
				System.out.println("\u001B[32m¡¡¡ Disfrute la funcion !!!");
				} else {
				System.out.println("\n\u001B[32mMuchas gracias por su visita.");
				System.out.println("\u001B[32m¡¡¡ Te esperamos proximamente !!!");
				}
				entradaUser.close();
				System.exit(0);
				break;

			// ************* OPCION INICIAL ERRONEA VOLVER A EMPEZAR
			default:
				System.out.println("\n\n\n\n\n");
				System.out.println("\u001B[31m\u001B[47mERROR: La opcion ingresada no existe.\u001B[0m");
				break;
			}
		} // ************* AQUI TERMINA EL FOR
	}
}