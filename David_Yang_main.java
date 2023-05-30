import java.io.IOException;

public class David_Yang_main {
	public static void main(String args[]) throws IOException {
		String inFile=args[0];
		int bucketsize=Integer.parseInt(args[1]);
		String outFile1=args[2];
		String outFile2=args[3];
		David_Yang_hashTable HT= new David_Yang_hashTable(bucketsize);
		HT.informationProcessing(inFile,outFile2);
		HT.printHashTable(outFile1);
	}
}
