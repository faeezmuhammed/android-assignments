using System;
using System.Collections.Generic;
using System.Text;


    /// <summary>
    /// Function definition class (keeps function name, parameter counts, and delegate).
    /// </summary>
    public class FunctionDefinition
    {
        // ** fields
        public int ParmMin, ParmMax;
        public  CalcEngine.CalcEngineFunction Function;

        // ** ctor
        public FunctionDefinition(int parmMin, int parmMax, CalcEngine. CalcEngineFunction function)
        {
            ParmMin = parmMin;
            ParmMax = parmMax;
            Function = function;
        }
    }

