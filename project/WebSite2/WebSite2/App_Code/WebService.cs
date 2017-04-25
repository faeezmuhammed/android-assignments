using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;
using System.IO;
using MathNet.Numerics.LinearAlgebra;
/// <summary>
/// Summary description for WebService
/// </summary>
[WebService(Namespace = "http://tempuri.org/")]
[WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
// To allow this Web Service to be called from script, using ASP.NET AJAX, uncomment the following line. 
// [System.Web.Script.Services.ScriptService]
public class WebService : System.Web.Services.WebService
{
    public WebService()
    { }
    [WebMethod]

    public int findequalsymbol(String a)
    {
        int flg = 0;
        foreach (char c in a)
        {
            if (c == '=')
                flg++;
        }
        return flg;
    }
    [WebMethod]
    
    public String evaluate(String a)
    {
        try
        {
            byte[] aa = System.Text.ASCIIEncoding.ASCII.GetBytes(a);
            a = System.Text.ASCIIEncoding.ASCII.GetString(aa);

            a = a.Replace("?", "");

            a = a.Replace(" ", "");
            a = a.Replace("\0", "");

            int toteq = findequalsymbol(a);

            if (toteq == 1)
            {
               return q(a);
            }
            else if (toteq > 1)
            {
                return l(a);
            }
            else
            {
                CalcEngine ce = new CalcEngine();
                var x = ce.Parse(a);
                var value = (double)x.Evaluate();
                return value.ToString();
            }
        }
        catch
        {
            return "";
        }
        return "Equation is not available";
    }
    int a1cmp=1, b1cmp=1, c1cmp=1;
    public void getindex(String a)
    {
        a1cmp = 1; b1cmp = 1; c1cmp = 1;
        List<int> aa = new List<int>();
        for (int i = a.Length - 1; i >= 0; i--)
        {
            if (a[i] == '-')
            {
                aa.Add(i);
            }
        }
        for (int i = 0; i < aa.Count; i++)
        {
            string tm = a.Substring(aa[i]);




            String [] s1= tm.Split(new String[] { "+", "-" }, StringSplitOptions.RemoveEmptyEntries);


            if (s1[0].ToUpper().Contains(val1.ToUpper()))
            {
                a1cmp = -1;
            }
            else if (s1[0].ToUpper().Contains(val2.ToUpper()))
            {
                b1cmp = -1;
            }
            else
            {
                c1cmp = -1;
            }

        }

    }
    [WebMethod]
    public string q(string a)  ///quadratic
    {
        float a1 = 0, b1 = 0, c1 = 0;
        String[] b = a.Split('=');
        if (b.Length == 2)
        {
            String[] k = b[0].Split(new String[] { "-", "+" }, StringSplitOptions.RemoveEmptyEntries);
            int l = int.Parse(b[1].Trim());
            if (k.Length == 3)
            {
                for (int m = 0; m < k.Length; m++)
                {
                    k[m] = k[m].Trim().ToUpper();
                    if (k[m].Contains("X2"))
                    {
                        try
                        {
                            a1 = int.Parse(k[m].Replace("X2", ""));
                        }
                        catch
                        {
                            a1 = 1;
                        }
                    }
                    else if (k[m].Contains("X"))
                    {
                        try
                        {
                            b1 = int.Parse(k[m].Replace("X", ""));
                        }
                        catch
                        {
                            b1 = 1;
                        }
                    }
                    else
                    {
                        try
                        {
                            c1 = int.Parse(k[m]);
                        }
                        catch
                        {
                            c1 = 0;
                        }
                        c1 = c1 - l;
                    }
                }
                val1 = "X2";
                val2 = "X";

                getindex(b[0]);

                a1 = a1 * a1cmp;
                b1 = b1 * b1cmp;
                c1 = c1 * c1cmp;
                
                if (Math.Abs(a1) > 0 && Math.Abs(b1) > 0 && Math.Abs(c1) > 0)
                {
                    float tmp = (b1 * b1) - (4 * a1 * c1);
                    double discriminant = Math.Sqrt(tmp);

                    String op = "Quadratic equation" + Environment.NewLine;
                    op =op+ "                      " + "__________"+Environment.NewLine;
                    op=op+"      "+@"-b(+/-)  \/b2-4ac"+Environment.NewLine;
                    op=op+"X=----------------------------------------"+ Environment.NewLine;
                    op= op+"               2a"+Environment.NewLine;
                    op = op + " A=" + a1 + "\n B=" + b1 + "\n C=" + c1 + Environment.NewLine;

                    op =op+ "Equation" + Environment.NewLine;
                    op = op + "                            " + "__________" + Environment.NewLine;
                    op = op + "      " + @"-("+b1+@")(+/-)  \/("+b1+")2-4("+ a1+"*"+c1 +")" +Environment.NewLine;
                    op = op + "X=----------------------------------------" + Environment.NewLine;
                    op = op + "               2*" + a1+ Environment.NewLine;

                    op=op+"\n"+"Discrimininant" + "\n" + "----------------" +"\n (b2-4ac)=("+ b1 +"*"+b1+")-(4*"+a1+"*"+c1+")="+ tmp + Environment.NewLine;

                    op = op + "   _______________" + Environment.NewLine;
                    op= op+ @"\/("+b1+"*"+b1+")-4*"+a1+"*"+c1+"  ="+discriminant+ Environment.NewLine;

                    if (tmp > 0)
                    {
                        double x1 = ((-1 * b1) + discriminant) / (2 * a1);
                        op = op + Environment.NewLine;
                        
                        double x2 = ((-1 * b1) - discriminant) / (2 * a1);
                        //////////////////////////////////////////////////////////////////////////////////////
                        op = op + "                       " + "_______________" + Environment.NewLine;
                        op = op + "      " + @"-(" + b1 + @")(-)  \/(" + b1 +"*"+b1+")-4(" + a1 + "*" + c1 + ")" + Environment.NewLine;
                        op = op + "X=----------------------------------------=" + Environment.NewLine;
                        op = op + "               2*" + a1 + Environment.NewLine;

                        op = op + "=" + x1 + Environment.NewLine;

                        //////////////////////////////////////////////////////////////////////////////////////

                        //////////////////////////////////////////////////////////////////////////////////////
                        op = op + "                       " + "_______________" + Environment.NewLine;
                        op = op + "      " + @"-(" + b1 + @")(+)  \/(" + b1 +"*"+ b1+ ")-4(" + a1 + "*" + c1 + ")" + Environment.NewLine;
                        op = op + "X=----------------------------------------=" + Environment.NewLine;
                        op = op + "               2*" + a1 + Environment.NewLine;

                        op = op + "=" + x2+ Environment.NewLine;

                        //////////////////////////////////////////////////////////////////////////////////////







                        op = op + "\n" + "Root are  " + x1 + " and " + x2 +"\n -------------------------";

                        //return x1 + "-----------" + x2;
                        return op;
                    }
                    else if (tmp == 0)
                    {
                        double x1 = ((-1 * b1) + discriminant) / (2 * a1);
                        double x2 = ((-1 * b1) - discriminant) / (2 * a1);

                        //////////////////////////////////////////////////////////////////////////////////////
                        op = op + "                             " + "__________" + Environment.NewLine;
                        op = op + "      " + @"-(" + b1 + @")(-)  \/(" + b1 + "*" + b1 + ")-4(" + a1 + "*" + c1 + ")" + Environment.NewLine;
                        op = op + "X=----------------------------------------=" + Environment.NewLine;
                        op = op + "               2*" + a1 + Environment.NewLine;

                        op = op + "=" + x1 + Environment.NewLine;

                        //////////////////////////////////////////////////////////////////////////////////////




                         op = op + "\n" + "Root are  " + x1 ;
                        // return x1 + "-----------" + x2;
                        return op;


                       
                    }
                    else if (tmp < 0)
                    {
                        return "No real roots available";
                    }
                }
            }
            else
            {
                return "its not a quadratic equation";
            }
        }
        return "";
    }
    String val1 = "", val2 = "", val3 = "";
    [WebMethod]
    public string l(string a)
    {
        a = a.ToUpper();
        a=a.Replace("i", "1");
        a = a.Replace("L", "1");




        String [] allcharss= new String [] {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
        List<string> chars= new List<string>();
        for(int i=0; i< a.Length;i++)
        {
            if(allcharss.Contains(a[i].ToString()))
            {
                if(chars.Contains(a[i]+"")==false)
                   chars.Add(a[i]+"");
            }
        }
        if (chars.Count == 2)
        {
            val1 = chars[0].ToLower();
            val2 = chars[1].ToLower();
            return linear2var(a).ToLower();
        }
        else if (chars.Count == 3)
        {
            val1 = chars[0].ToLower();
            val2 = chars[1].ToLower();
            val3 = chars[2].ToLower();
            return linear3var(a);
        }
        return "";
    }
    [WebMethod]
    public string evl()
    {
                  var A = MathNet.Numerics.LinearAlgebra.Matrix<double>.Build.DenseOfArray(new double[,] {
                  { 3, 2, -1 },
                  { 2, -2, 4 },
                  { -1, 0.5, -1 }
                  });
        
                  var b = Vector<double>.Build.Dense(new double[] { 1, -2, 0 });
                  var x = A.Solve(b);

                  return "";
    }
    //linear 3 variable
    public string linear3var(string aa)
    {
        float a1 = 0, b1 = 0, c1 = 0;
        String[] a = aa.Split(new String[] { "\n" }, StringSplitOptions.RemoveEmptyEntries);

        List<double> l1, l2, l3,o1;
        l1 = new List<double>();
        l2 = new List<double>();
        l3 = new List<double>();
        o1 = new List<double>();

        for (int j = 0; j < a.Length; j++)
        {
            String[] b = a[j].Split('=');
            if (b.Length == 2)
            {
                String[] k = b[0].Split(new String[] { "-", "+" }, StringSplitOptions.RemoveEmptyEntries);
                int l = int.Parse(b[1].Trim());
                if (k.Length == 3)
                {
                    for (int m = 0; m < k.Length; m++)
                    {
                        k[m] = k[m].Trim().ToLower();
                        if (k[m].Contains(val1))
                        {
                            try
                            {
                                a1 = int.Parse(k[m].Replace(val1, ""));
                            }
                            catch
                            {
                                a1 = 1;
                            }
                        }
                        else if (k[m].Contains(val2))
                        {
                            try
                            {
                                b1 = int.Parse(k[m].Replace(val2, ""));
                            }
                            catch
                            {
                                b1 = 1;
                            }
                        }
                        else if (k[m].Contains(val3))
                        {
                            try
                            {
                                c1 = int.Parse(k[m].Replace(val3, ""));
                            }
                            catch {
                                c1 = 1;
                            }

                        }
                    }
                    getindex(b[0]);

                    a1 = a1 * a1cmp;
                    b1 = b1 * b1cmp;
                    c1 = c1 * c1cmp;
                    
                    l1.Add((int)a1);
                    l2.Add((int)b1);
                    l3.Add((int)c1);
                    o1.Add(l);
                }
                else
                {
                    
                }
            }
           
        }

        //checking 3 equations
        if (l1.Count == 3 && l2.Count == 3 && l3.Count == 3)
        {
            var A = MathNet.Numerics.LinearAlgebra.Matrix<double>.Build.DenseOfArray(new double[,] {
                { l1[0],l2[0],l3[0]},
                { l1[1],l2[1],l3[1]},
                { l1[2],l2[2],l3[2]}
                  });

            String op = "\n" + l1[0] + "  " + l2[0] + "  " + l3[0] + "     " + o1[0] + "\n" + l1[1] + "  " + l2[1] + "  " + l3[1] + "     " + o1[1]  +"\n" + l1[2] + "  " + l2[2] + "  " + l3[2] +"     "+o1[2] + "\n"; 
            var b = Vector<double>.Build.Dense(o1.ToArray());
            var x = A.Solve(b);
            return op+ "Results are"+"\n"+ val1 +":"+ x[0] + "\n"+ val2+":" + x[1] + "\n" +val3+":"+ x[2];
        }
        else
        {
            return "Scanning error";
        }
        return "";
    }
    ////linear 2 variable equation solving
    public string linear2var(string aa)
    {
        float a1 = 0, b1 = 0, c1 = 0;
        String[] a = aa.Split(new String[] { "\n" }, StringSplitOptions.RemoveEmptyEntries);


        List<double> l1, l2, o1;
        l1 = new List<double>();
        l2 = new List<double>();
      
        o1 = new List<double>();

        for (int j = 0; j < a.Length; j++)
        {

            String[] b = a[j].Split('=');
            if (b.Length == 2)
            {
                String[] k = b[0].Split(new String[] { "-", "+" }, StringSplitOptions.RemoveEmptyEntries);
                int l = int.Parse(b[1].Trim());
                if (k.Length == 2)
                {
                    for (int m = 0; m < k.Length; m++)
                    {
                        k[m] = k[m].Trim().ToLower();
                        if (k[m].Contains(val1))
                        {
                            try
                            {
                                a1 = int.Parse(k[m].Replace(val1, ""));
                            }
                            catch
                            {
                                a1 = 1;
                            }


                        }
                        else if (k[m].Contains(val2))
                        {
                            try
                            {
                                b1 = int.Parse(k[m].Replace(val2, ""));
                            }
                            catch
                            {
                                b1 = 1;
                            }
                        }
                      
                    }

                    getindex(b[0]);

                    a1 = a1 * a1cmp;
                    b1 = b1 * b1cmp;
                 

                    l1.Add((int)a1);
                    l2.Add((int)b1);
                    

                    o1.Add(l);




                }
                else
                {

                }
            }

        }

        if (l1.Count == 2 && l2.Count == 2 )
        {
            var A = MathNet.Numerics.LinearAlgebra.Matrix<double>.Build.DenseOfArray(new double[,] {
                { l1[0],l2[0]},
                { l1[1],l2[1]},
               
                });
            
            var b = Vector<double>.Build.Dense(o1.ToArray());
            var x = A.Solve(b);
            String op = "\n" + l1[0] + "    " + l2[0] + "     " + o1[0] + "\n" + l1[1] + "    " + l2[1] + "     " + o1[1] + "\n";// +l1[2] + "    " + l2[2] + "     " + o1[2] + "\n";
            return op + "Results are" + "\n" + val1 + ":" + x[0] + "\n" + val2 + ":" + x[1] ;

        }
        else
        {
            return "Scanning error";
        }
        return "";
    }
   
}
    