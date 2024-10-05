export type AuthContextProps = {
    user?: User, 
    setUser: Function,
    login: Function, 
    testeAPI: Function,
    logout: Function,
    register: Function,
    session: Function
}

export type User = {
    id: string,
    nome: string,
    email: string,
    cargo: string
}

export type LoginObject = {
    email: string,
    senha: string
}

export type RegisterObject = {
    nome: string,
    email: string,
    senha: string,
    cargo: string
}