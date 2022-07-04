
claim_form.onsubmit = async (e) => {
  e.preventDefault();

  let claim = {};
  let fd = new FormData(claim_form);
  fd.forEach( (v,k) => {claim[k] = v;});
  console.log(JSON.stringify(claim));

  const options = {
    method: 'POST',
    body: JSON.stringify(claim),
    headers: { 'Content-Type': 'application/json' }
  }

   fetch('/api/claims', options)
     .then(res => res.json())
     .then(res => updatePage(res.id, res.claimFromWho, res.dueDate, res.claimAmount))
     .catch(err => console.error(err));
};