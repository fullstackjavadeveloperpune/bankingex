import axios from 'axios';
import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom';

export const SignIn = () => {

    let navigate = useNavigate()

    const [customer, setCustomer] = useState({

        custEmailId:"",
        custPassword:"",
        role:""
    })

    const {custEmailId, custPassword, role} = customer;

    const onInputChange = (e)=>{
        setCustomer({...customer, [e.target.name]: e.target.value})
    }

    const onSubmit = async(e)=>{

        e.preventDefault();
        const result = await axios.post("http://localhost:8080/auth/signin", customer);

        localStorage.setItem("token", result.data)

        if(result.data){


            navigate(`/show`)
        }else{
            

            navigate(`/`)
        }

    }

  return (
    <div>

        <form onSubmit={(e)=> onSubmit(e)}>

            <div>
                Customer Email<input type='email' name='custEmailId' value={custEmailId} onChange={(e)=> onInputChange(e)} />
            </div>

            <div>
                Customer Password<input type='password' name='custPassword' value={custPassword} onChange={(e)=> onInputChange(e)} />
            </div>

            <div>
                Role<input type='text' name='role' value={role} onChange={(e)=> onInputChange(e)} />


            </div>


            <button className='btn btn-success'>SignIn</button>

        </form>
    </div>
  )
}
