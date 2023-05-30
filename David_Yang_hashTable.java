import java.io.*;

public class David_Yang_hashTable {
	public char op;
	public String data;
	public int bucketSize;
	public David_Yang_listNode[] hashTable;
	
	public David_Yang_hashTable(int s) {
		bucketSize=s;
		hashTable=new David_Yang_listNode[bucketSize];
		for(int i=0 ; i<bucketSize; i++) {
			hashTable[i]=new David_Yang_listNode("dummy");
		}
	}
	
	public int doIt(String s) {
		long value=1;
		char oneCh;
		for(int i=0; i<s.length();i++) {
			oneCh=s.charAt(i);
			value=value*32+(int)oneCh;
		}
		return (int) (value%bucketSize);
	}
	
	public void informationProcessing (String inFile, String outFile2) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(inFile));
		BufferedWriter writer = new BufferedWriter(new FileWriter(outFile2));
		String line;
		writer.write("Enter informationProcessing method\n");
		while((line = reader.readLine()) != null) {
			String words[]=line.split("\t");
			op=line.charAt(0);
			data=words[1];
			writer.write("original data: " + words[0] + "\t" + words[1]+"\n");
			int index=doIt(words[1]);
			writer.write("hash number: " + index+"\n");
			printList (index, writer);
			if(op=='+') {
				hashInsert (index, data, writer);
			}else if(op == '-') {
				hashDelete (index, data, writer);
			}else if(op == '?') {
				hashRetrieval (index, data, writer);
			}else {
				writer.write("op is an unrecognize operation!\n");
			}
		}reader.close();
		writer.close();
	}
	
	public David_Yang_listNode findSpot(int index, String d) {
		David_Yang_listNode spot=hashTable[index];
		while(spot.next!=null&&spot.next.data.compareTo(data)<0) {
			spot=spot.next;
		}
		return spot;
	}
	
	public void hashInsert(int index, String d, BufferedWriter writer) throws IOException {
		//BufferedWriter writer = new BufferedWriter(new FileWriter(outFile2));
		writer.write( "*** enter hashInsert method. Performing hashInsert\n ");
		David_Yang_listNode spot=findSpot(index, d);
		if (spot.next != null && spot.next.data == data){
			writer.write("*** Warning, data is already in the database!\n\n");
		}else { 
			David_Yang_listNode newNode=new David_Yang_listNode(d);
			newNode.next=spot.next;
			spot.next=newNode;
			writer.write(" After hashInsert operation бн ");
			printList(index, writer);
		}
	}
	
	public void hashDelete(int index, String d, BufferedWriter writer) throws IOException {
		//BufferedWriter writer = new BufferedWriter(new FileWriter(outFile2));
		writer.write( "** Inside hashDelete method. Performing hashDelete\n ");
		David_Yang_listNode spot=findSpot(index, d);
	
		if (spot.next == null || spot.next.data.compareTo(d)!=0){
			writer.write("*** Warning, data is *not* in the database!\n\n");
		}else { 
			David_Yang_listNode temp=spot.next;
			spot.next=temp.next;
			temp.next=null;
			writer.write(" After hashDelete operation бн ");
			printList(index, writer);
		}
	}
	
	public void hashRetrieval (int index, String d, BufferedWriter writer) throws IOException {
		//BufferedWriter writer = new BufferedWriter(new FileWriter(outFile2));
		writer.write("** Inside hashRetrieval. Performing hashRetrieval\n");
		David_Yang_listNode spot=findSpot(index, d);
		if (spot.next == null || spot.next.data.compareTo(d)!=0) {
			writer.write("*** Warning, the record is *not* in the database!\n");
		}else {
			writer.write("Yes, the record is in the database!\n\n");
		}
	}
	
	public void printList(int index, BufferedWriter writer ) throws IOException {
		//BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));
		writer.write("HashTable["+index+"]: ");
		David_Yang_listNode curr=hashTable[index];
		while(curr.next!=null) {
			writer.write("("+curr.data+", "+curr.next.data+")->");
			curr=curr.next;
		}
		writer.write("("+curr.data+", "+"end)->End\n\n");
	}
	
	public void printHashTable (String outFile) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));
		for(int i=0; i<bucketSize; i++) {
			printList(i, writer);
		}writer.close();
	}
	
}
