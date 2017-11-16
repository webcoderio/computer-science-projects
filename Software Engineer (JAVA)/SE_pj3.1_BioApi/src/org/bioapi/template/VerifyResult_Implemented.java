/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bioapi.template;

import org.bioapi.Matching;
import org.bioapi.data.BIR;
import org.bioapi.data.Payload;
/**
 *
 * @author Ashwin Mohan
 */

	public class VerifyResult_Implemented implements
			Matching.VerifyResult, org.bioapi.AttachSession.VerifyResult
	{
        int Vresult=0;
		public VerifyResult_Implemented(int Vresult){
            this.Vresult=Vresult;
        }

        public BIR getAuditData(){

			return null;
		}

		public boolean hasAuditData(){

				return false;
		}

		public boolean hasPayload(){

			return false;
		}

		public boolean hasAdaptedBIR(){

			return false;
		}

		public boolean getResult(){

            if(this.Vresult == 0)
            return false;

        else return true;
		}

		public Payload getPayload(){

			return null;
		}

		public int getFMRachieved(){

			return -1;
		}

		public BIR getAdaptedBIR(){

			return null;
		}
	}