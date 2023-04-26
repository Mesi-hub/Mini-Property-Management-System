import { apiSecured, apiUnsecured } from "..";

const fetchAllOwner = async () => {
    let result = await apiSecured().get("/owners");
    return result.data;
};

const approveOwner = async (id, status) => {
    let result = await apiSecured().put("/owners/approve/" + id, {
        approved: status,
    });
    return result.data;
};

const blackListOwner = async (id, status) => {
    let result = await apiSecured().put("/owners/black-list/" + id, {
        blackListed: status,
    });
    return result.data;
};

export { fetchAllOwner, approveOwner, blackListOwner };
