import axios from 'axios'
import React, { useEffect, useState } from 'react'
import { Link } from 'react-router-dom'

export const Show = () => {


    const [customers, setCustomers] = useState([])

    useEffect(()=>{

        loadCustomers()

    }, [])


    const loadCustomers = async()=>{


        const result = await axios.get("http://localhost:8080/customers/findall")

        setCustomers(result.data)

        console.log(result.data)

    }
  return (
    <div>

        <table className='table table-hover'>

            <thead>
                <tr>
                    <th>Customer ID</th>
                    <th>Account Number</th>
                    <th>Customer Name</th>
                    <th>Customer Address</th>
                    <th>Contact Number</th>
                    <th>Account Balance</th>
                    <th>Customer DOB</th>
                    <th>Customer Status</th>
                    <th>Customer UID</th>
                    <th>PAN CARD</th>
                    <th>Customer Email</th>
                    <th>Customer Password</th>
                    <th>Customer Role</th>
                    <th>Action</th>
                </tr>
            </thead>

            <tbody>
                {
                    customers.map((customer)=>(
                        <tr>
                            <td>{customer.custId}</td>
                            <td>{customer.custAccountNumber}</td>
                            <td>{customer.custName}</td>
                            <td>{customer.custAddress}</td>
                            <td>{customer.custContactNumber}</td>

                            <td>{customer.custAccountBalance}</td>
                            <td>{customer.custDOB}</td>
                            <td>{customer.custStatus}</td>
                            <td>{customer.custUID}</td>

                            <td>{customer.custPanCard}</td>
                            <td>{customer.custEmailId}</td>


                            <td>{customer.custPassword}</td>
                            <td>{customer.role}</td>

                            <td>
                                <button className='btn btn-danger'>Delete</button>

                                <Link className='btn btn-info'>Update</Link>
                            </td>
                        </tr>
                    ))
                }
            </tbody>
        </table>

    </div>
  )
}
