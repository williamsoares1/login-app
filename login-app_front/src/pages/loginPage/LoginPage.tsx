import React, { useEffect, useState } from 'react';
import { FaUserAstronaut } from "react-icons/fa";
import { Input_component } from '../../component/input/InputComp';
import { Password_input } from '../../component/password_input/PasswordInputComp';
import { useUser } from '../../context/auth/AuthContext';
import '../../css/pages/loginPage.css';

export const LoginPage = () => {
  const [email, setEmail] = useState<string>();
  const [senha, setSenha] = useState<string>();
  const loginObject = {email, senha};

  const {refresh, login, testeAPI} = useUser();

  useEffect(() => {
    refresh("/home");
  }, [])

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    login(loginObject)
  }

  const handleTeste = async (e: React.FormEvent) => {
    e.preventDefault();

    testeAPI()
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
            <Input_component max={40} placeholder={"ex: user@email.com"} type={"text"} value={email} setValue={setEmail} label={"email"}/>
            <Password_input max={12} placeholder={"password"} type={"password"} value={senha} setValue={setSenha} label={"senha"}/>
            <button type='submit'>SUBMIT</button>
            <button onClick={handleTeste}>TESTE</button>
          </form>
        </section>
      </main>
    </>
  )
}
