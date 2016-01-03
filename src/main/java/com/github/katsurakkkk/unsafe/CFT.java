package com.github.katsurakkkk.unsafe;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

public class CFT {
	
	static final class A {
		private int[] arrayA = new int[10];
		
		private int a;
		private long b;
		
		private long[] arrayB = new long[20];
		private CFT[] arrayC = new CFT[30];
		
        private static final long addressA;
        private static final long addressB;
        private static final long addressC;
        
        private static final long scaleA;
        private static final long scaleB;
        private static final long scaleC;
		
        private static final Unsafe U;
        static {
			try {
				Class<?> clazz = Unsafe.class;
				Field f;
				f = clazz.getDeclaredField("theUnsafe");
				f.setAccessible(true);
				U = (Unsafe) f.get(clazz);
				
//				U = sun.misc.Unsafe.getUnsafe();
				addressA = U.arrayBaseOffset(int[].class);
				addressB = U.arrayBaseOffset(long[].class);
				addressC = U.arrayBaseOffset(CFT[].class);
				scaleA = U.arrayIndexScale(int[].class);
				scaleB = U.arrayIndexScale(long[].class);
				scaleC = U.arrayIndexScale(CFT[].class);
			} catch (Exception e) {
                throw new Error(e);
            }
        }
		/**
		 * @return the arrayA
		 */
		public int[] getArrayA() {
			return arrayA;
		}
		/**
		 * @param arrayA the arrayA to set
		 */
		public void setArrayA(int[] arrayA) {
			this.arrayA = arrayA;
		}
		/**
		 * @return the a
		 */
		public int getA() {
			return a;
		}
		/**
		 * @param a the a to set
		 */
		public void setA(int a) {
			this.a = a;
		}
		/**
		 * @return the b
		 */
		public long getB() {
			return b;
		}
		/**
		 * @param b the b to set
		 */
		public void setB(long b) {
			this.b = b;
		}
		/**
		 * @return the arrayB
		 */
		public long[] getArrayB() {
			return arrayB;
		}
		/**
		 * @param arrayB the arrayB to set
		 */
		public void setArrayB(long[] arrayB) {
			this.arrayB = arrayB;
		}
		/**
		 * @return the arrayC
		 */
		public CFT[] getArrayC() {
			return arrayC;
		}
		/**
		 * @param arrayC the arrayC to set
		 */
		public void setArrayC(CFT[] arrayC) {
			this.arrayC = arrayC;
		}
		/**
		 * @return the addressa
		 */
		public static long getAddressa() {
			return addressA;
		}
		/**
		 * @return the addressb
		 */
		public static long getAddressb() {
			return addressB;
		}
		/**
		 * @return the addressc
		 */
		public static long getAddressc() {
			return addressC;
		}
		/**
		 * @return the scalea
		 */
		public static long getScalea() {
			return scaleA;
		}
		/**
		 * @return the scaleb
		 */
		public static long getScaleb() {
			return scaleB;
		}
		/**
		 * @return the scalec
		 */
		public static long getScalec() {
			return scaleC;
		}
		/**
		 * @return the u
		 */
		public static Unsafe getU() {
			return U;
		}
	}
}
