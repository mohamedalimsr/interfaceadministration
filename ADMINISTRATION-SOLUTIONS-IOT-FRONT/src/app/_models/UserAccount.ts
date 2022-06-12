import { Role } from "./role";

export class AdminAccount{
    idAdministratorCompte: number;
    username: string;
    password: string;
    role: string;
    useFcm: boolean;
    fcmApikey: string;
    fcmPrefix: string;
    mailSupport: string;
    deviceCostByDay: number;
    accountFreePerMonth: number;
    transctionFee: number;

    constructor(){

    }
}

export class ServerClientAccount{
    idServerClientAccount: number;
    username: string;
    email: string;
    password: string;
    pseudo:string;
    ipAdresse: string;
    intervaleStart: number;
    intervaleEnd: number;
    dateCreation: Date;
    dateExpiration: Date;
    administratorCompte: AdminAccount;
    role: Role;
    constructor(){
        
    }
}

export class WebClientAccount{
    idCompteClientWeb: number;
    login: string;
    password: string;
    rawPassword: string;
    date_creation: Date;
    date_expiration: Date;
    code_pays: string;
    pool: number;
    telephone: number;
    area: string;
    compteClientServer: ServerClientAccount = new ServerClientAccount();
    administratorCompte: AdminAccount;
    notificationSubquery: string;
    mobileNotif: boolean;
    deviceFeeByDay: number;
    accountFeeByMonth: number;
    deviceFeePerMonth: number;
    simCardFeePerMonth: number;
    username: string;
    email: string;
    expired: boolean;
    role: Role;
    serverAccount: ServerClientAccount;
    constructor(){
        
    }
}