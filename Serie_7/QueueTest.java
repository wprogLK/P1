class QueueTest{
	public static void main(String[] args){
		Queue printQueue =  new Queue();

		// Testing dequeue() on an empty queue... 
		try{
			System.out.println(printQueue.dequeue());
		}catch(EmptyQueueException e){
			System.out.println("Queue is empty!");
		}

		// fill print queue with jobs...
		printQueue.enqueue(new PrintJob("Hi, I'm Alice.", "alice"));
		printQueue.enqueue(new PrintJob("Hi, I'm Bob and I've been living next door to\nAlice for 24 years.", "bob"));
		printQueue.enqueue(new PrintJob("Hi, I'm Anna.", "anna"));

		// dequeue all print jobs until queue is empty
		while(!printQueue.isEmpty()){
			try{
				System.out.println(printQueue.dequeue());
			}catch(EmptyQueueException e){
				break;
			}
		}
	}
}

class PrintJob{
	private String text;
	private String username;
	private int status;

	public PrintJob(String text, String username){
		this.text = text;
		this.username = username;
	}

	public String toString(){
		return "* print job of "+username+": "+text;
	}
}
