import {post} from "../plugins/ajax";

export const loginUser = (username,password) =>
    post("/bank_book/user/login/",{
        username:username,
        password:password
    });

