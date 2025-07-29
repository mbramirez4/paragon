package paragon.Model.Abilities;

import java.util.ArrayList;

import paragon.Service.JsonStorable;
import paragon.Service.FilesManager;

public class AbilityManager implements JsonStorable {
    private static final String ROOT_DIRECTORY = "app/src/main/java/paragon/config";
    
    private String filePath;
    private ArrayList<Ability> abilities = new ArrayList<Ability>();
    
    public AbilityManager() {
        this.filePath = ROOT_DIRECTORY + "/abilities.json";
        this.abilities = loadAbilities();
    }

    public String getFilePath() {
        return filePath;
    }

    public String getRootDirectory() {
        return ROOT_DIRECTORY;
    }

    public ArrayList<Ability> getAbilities() {
        return abilities;
    }

    public void addAbility(Ability ability) {
        abilities.add(ability);
    }

    private ArrayList<Ability> loadAbilities() {
        Ability[] tmpAbilities = FilesManager.loadFromJson(this, Ability[].class);
        for (Ability ability: tmpAbilities) {
            addAbility(ability);
        }
        return abilities;
    }

    public void save() {
        FilesManager.saveToJson(this);
    }

    public Ability[] sampleAbilities(int n) {
        Ability[] sampleAbilities = new Ability[n];
        
        ArrayList<Integer> indices = new ArrayList<Integer>();
        for (int i = 0; i < n;) {
            int j = (int) Math.floor(Math.random() * abilities.size());
            if (indices.contains(j) || abilities.get(j) == null) {
                continue;
            }
            
            sampleAbilities[i] = abilities.get(j);
            indices.add(j);
            i++;
        }
        return sampleAbilities;
    }

    
}
