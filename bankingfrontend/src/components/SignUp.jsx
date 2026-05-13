import axios from 'axios';
import React, { useState } from 'react'

export const SignUp = () => {


    const[customer, setCustomer] = useState({
        custAccountNumber:"",
        custName:"",
        custAddress:"",
        custContactNumber:"",
        custAccountBalance:"",
        custDOB:"",
        customerStatus:"",
        custUID:"",
        custPanCard:"",
        custEmailId:"",
        custPassword:"",
        role:""
    })

    const{custAccountNumber, custName, custAddress, custContactNumber, custAccountBalance, custDOB, customerStatus, custUID, custPanCard, custEmailId, custPassword, role}= customer;

    const onInputChange = (e)=>{

        setCustomer({...customer, [e.target.name]: e.target.value})

    }


    const onSubmit = async(e)=>{

        e.preventDefault();

        await axios.post("http://localhost:8080/auth/signup", customer)

        alert("Please check data in PostgreSQL DB")

    }

  return (
    <div>

        <form onSubmit={(e)=> onSubmit(e)}>

            <div>
                Customer Account Number<input type='number' name='custAccountNumber' value={custAccountNumber} onChange={(e)=> onInputChange(e)} />

            </div>


             <div>
                Customer Name<input type='text' name='custName' value={custName} onChange={(e)=> onInputChange(e)} />
                
            </div>


             <div>
                Customer Address<input type='text' name='custAddress' value={custAddress} onChange={(e)=> onInputChange(e)} />
                
            </div>

             <div>
                Customer Contact Number<input type='number' name='custContactNumber' value={custContactNumber} onChange={(e)=> onInputChange(e)} />
                
            </div>

             <div>
                Customer Account Balance<input type='number' name='custAccountBalance' value={custAccountBalance} onChange={(e)=> onInputChange(e)} />
                
            </div>

             <div>
                Customer DOB<input type='date' name='custDOB' value={custDOB} onChange={(e)=> onInputChange(e)} />
                
            </div>

             <div>
                Customer Status<input type='text' name='customerStatus' value={customerStatus} onChange={(e)=> onInputChange(e)} />
                
            </div>

             <div>
                Customer UID<input type='number' name='custUID' value={custUID} onChange={(e)=> onInputChange(e)} />
                
            </div>

             <div>
                Customer Pan Card<input type='text' name='custPanCard' value={custPanCard} onChange={(e)=> onInputChange(e)} />
                
            </div>

              <div>
                Customer Email<input type='email' name='custEmailId' value={custEmailId} onChange={(e)=> onInputChange(e)} />
                
            </div>

              <div>
                Customer Password<input type='password' name='custPassword' value={custPassword} onChange={(e)=> onInputChange(e)} />
                
            </div>

              <div>
                Role<input type='text' name='role' value={role} onChange={(e)=> onInputChange(e)} />
                
            </div>

            <button type='submit' className='btn btn-success'>SignUp</button>

        </form>

    </div>
  )
}
