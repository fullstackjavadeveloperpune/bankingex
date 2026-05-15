import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from './assets/vite.svg'
import heroImg from './assets/hero.png'
import './App.css'
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'
import { SignIn } from './components/SignIn'
import { SignUp } from './components/SignUp'
import { Show } from './components/Show'
import { Deposit } from './components/Deposit'


function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      
      <Router>
        <Routes>
          <Route exact path='/' element={<SignIn />} />

          <Route exact path='/signup' element={<SignUp />} />

          <Route exact path='/show' element={<Show />} />

          <Route exact path='/deposit' element={<Deposit />} />

          

        
        </Routes>
      </Router>

    </>
  )
}

export default App
