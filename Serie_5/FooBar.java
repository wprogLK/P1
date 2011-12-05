
public class FooBar {

	public FooBar()
	{
		start();
	}
	/**
	 * @param args
	 */
	public void start()
	{
		new A().bla();
		new A().foo();
		new A().bar();
		new B().bla();
		new B().foo();
		new B().bar();
		new C().bla();
		new C().foo();
		new C().bar();
	}
	
	private class A 
	{
		protected String bla ;
		public A (){ this . bla = " Hello from A "; }
		public void bla (){ System . out . println ( this . bla ); }
		public void foo (){ System . out . println (" A . foo "); }
		public void bar (){ this . foo (); }
	}
	private class B extends A 
	{
		public B ()
		{
			super ();
			this . bla = " Hello from B ";
		}
	}
	private class C extends B 
	{
		public C (){ super (); }
//		public void foo (){ System . out . println (" C . foo "); }
		//public void foo (){ this.bar(); }
	}
	
	
	

}
