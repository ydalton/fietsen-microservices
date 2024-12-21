import { atom } from "recoil";

export const tokenState = atom({
  key: 'tokenState',
  default: ""
});

export const userState = atom({
  key: 'userState',
  default: null
})
