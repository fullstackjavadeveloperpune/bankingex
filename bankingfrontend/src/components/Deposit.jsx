import axios from 'axios';
import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom';
import api from './axiosConfig';

export const Deposit = () => {

    let navigate = useNavigate()


    const[deposit, setDeposit] = useState({
        custAccountNumber:"",
        amount:""
    })

    const{custAccountNumber, amount} = deposit;

    const onInputChange = (e)=>{
        setDeposit({...deposit, [e.target.name]: e.target.value})
    }


    const onSubmit = async(e)=>{
        e.preventDefault()

        await api.patch(`/customers/deposit/${custAccountNumber}/${amount}`)

        alert("Amount Deposited Successfully")

        navigate(`/show`)


    }

  return (
    <div>

        <form onSubmit={(e)=> onSubmit(e)}>


            <div>
                Customer Account Number<input type='number' name='custAccountNumber' value={custAccountNumber} onChange={(e)=> onInputChange(e)} />
            </div>

            <div>
                Amount<input type='number' name='amount' value={amount} onChange={(e)=> onInputChange(e)} />
            </div>


            <button type='submit' className='btn btn-success'>Deposit</button>

        </form>

    </div>
  )
}
