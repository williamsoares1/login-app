import React, { useEffect, useState } from 'react';
import { FaUserAstronaut } from "react-icons/fa";
import { Input_component } from '../../component/input/InputComp';
import { Password_input } from '../../component/password_input/PasswordInputComp';
import { useUser } from '../../context/auth/AuthContext';
import '../../css/formPages.css';
import { useNavigate } from 'react-router-dom';

export const LoginPage = () => {
  const navigate = useNavigate();
  const [email, setEmail] = useState<string>();
  const [senha, setSenha] = useState<string>();
  const loginObject = {email, senha};

  const {refresh, login} = useUser();

  useEffect(() => {
    refresh("/home");
  }, [])

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    login(loginObject)
  }

  const registerPageRedirect = () => {
    navigate("/register");
  }

  return (
    <>
      <main>
        <section>
          <FaUserAstronaut size={100} color='var(--laranja)' />
          <h1>LOGIN</h1>
        </section>

        <section>
          <form onSubmit={handleSubmit}>
            <Input_component max={40} placeholder={"ex: usuario@email.com"} type={"text"} value={email} setValue={setEmail} label={"email"}/>
            <Password_input max={12} placeholder={"senha"} type={"password"} value={senha} setValue={setSenha} label={"senha"}/>
            <button type='submit'>SUBMIT</button>
          </form>
          <div className='register'>
            <h2>Não possui uma conta? <span onClick={registerPageRedirect}>Registrar-se</span></h2>
          </div>
        </section>
      </main>
    </>
  )
}
