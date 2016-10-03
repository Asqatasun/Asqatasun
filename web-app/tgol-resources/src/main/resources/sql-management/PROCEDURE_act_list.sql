
# List acts (id, URL, user_email) that are not completed
select
  ta.Id_Act,
  toe.Value,
  ta.Status,
  ta.Begin_Date,
  ta.End_Date,
  tu.Email1,
  tu.First_Name,
  tu.Name
FROM
  TGSI_ACT as ta
  INNER JOIN TGSI_CONTRACT as tc ON ta.CONTRACT_Id_Contract = tc.Id_Contract
  INNER JOIN TGSI_USER as tu ON tc.USER_Id_User = tu.Id_User
  INNER JOIN TGSI_CONTRACT_OPTION_ELEMENT as tcoe ON tc.Id_Contract = tcoe.CONTRACT_Id_Contract
  INNER JOIN TGSI_OPTION_ELEMENT as toe ON tcoe.OPTION_ELEMENT_Id_Option_Element = toe.Id_Option_Element
  INNER JOIN TGSI_OPTION as tgsio ON toe.OPTION_Id_Option = tgsio.Id_Option
WHERE
  tgsio.Code="DOMAIN"
  AND ta.Status != "COMPLETED";